package io.pivotal.literx.testing;

import io.pivotal.literx.domain.User;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

class AdaptTesting {

    // TODO Adapt Flux to RxJava Flowable
    Flowable<User> fromFluxToFlowable(Flux<User> flux) {
        return null;
    }

    // TODO Adapt RxJava Flowable to Flux
    Flux<User> fromFlowableToFlux(Flowable<User> flowable) {
        return null;
    }

    // TODO Adapt Flux to RxJava Observable
    Observable<User> fromFluxToObservable(Flux<User> flux) {
        return null;
    }

    // TODO Adapt RxJava Observable to Flux
    Flux<User> fromObservableToFlux(Observable<User> observable) {
        return null;
    }

    // TODO Adapt Mono to RxJava Single
    Single<User> fromMonoToSingle(Mono<User> mono) {
        return null;
    }

    // TODO Adapt RxJava Single to Mono
    Mono<User> fromSingleToMono(Single<User> single) {
        return null;
    }

    // TODO Adapt Mono to Java 8+ CompletableFuture
    CompletableFuture<User> fromMonoToCompletableFuture(Mono<User> mono) {
        return null;
    }

    // TODO Adapt Java 8+ CompletableFuture to Mono
    Mono<User> fromCompletableFutureToMono(CompletableFuture<User> future) {
        return null;
    }

}
