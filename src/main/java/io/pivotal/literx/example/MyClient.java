package io.pivotal.literx.example;

import reactor.core.publisher.Flux;

public class MyClient {

    private MyHandler myHandler = new MyHandler();

    public void doGreatWork() {
        myHandler.handle("a")
                .then(aVoid -> myHandler.handle("b"))
                .then(aVoid -> myHandler.handleMany(Flux.just("c", "d", "e", "f")))
                .then(aVoid -> myHandler.handle("g"))
                .block();

    }

    public static void main(String[] args) {
        new MyClient().doGreatWork();
    }

}
