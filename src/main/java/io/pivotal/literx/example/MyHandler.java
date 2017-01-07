package io.pivotal.literx.example;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.MonoSink;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class MyHandler {

    private final BlockingDeque<SomeMessage> workToDo = new LinkedBlockingDeque<>();

    Scheduler scheduler = Schedulers.single();
    Scheduler computeScheduler = Schedulers.elastic();

    private volatile boolean completed = false;

    public MyHandler() {
        scheduler.schedule(() -> {
            while (!completed) {
                try {
                    doSomeWork();
                } catch (InterruptedException e) {
                    System.err.println(e);
                    Thread.currentThread().interrupt();
                }
            }
        });
    }

    private void doSomeWork() throws InterruptedException {
        SomeMessage message = workToDo.take();
        // here we doing something
        Thread.sleep(500);
        System.out.println(message.getSomeString());
        computeScheduler.schedule(() -> {
            try {
                message.getCallback().onSuccessWithValue(null);
                //completes immediately
//                message.getCallback().onSuccess();
            } catch(Exception e) {
                e.printStackTrace();
                throw e;
            }
        });
    }

    public Mono<Void> handle(String someString) {
        return Mono.create(sink -> {
            Callback<Void> callback = new Callback<>(sink);
            workToDo.add(new SomeMessage<>(someString, callback));
        });
    }

    public Mono<Void> handleMany(Flux<String> someStrings) {
        return Mono.when(someStrings.flatMapSequential(
                this::handle
        ));
    }

    public void complete() {
        completed = true;
    }

    public class Callback<T> {

        MonoSink<T> sink;

        Callback(MonoSink<T> sink) {
            this.sink = sink;
        }

        void onSuccess() {
            sink.success();
        }

        void onSuccessWithValue(T data) {
            System.out.println(sink);
            sink.success(data);
        }

        void onFailure(Throwable ex) {
            sink.error(ex);
        }
    }

    public class SomeMessage<T> {

        private String someString;

        private Callback<T> callback;

        public SomeMessage(String someString, Callback<T> callback) {
            this.someString = someString;
            this.callback = callback;
        }

        public String getSomeString() {
            return someString;
        }

        public Callback<T> getCallback() {
            return callback;
        }
    }
}
