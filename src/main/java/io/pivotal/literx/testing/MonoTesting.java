package io.pivotal.literx.testing;

import reactor.core.publisher.Mono;

class MonoTesting {
    // TODO Return an empty Mono
    Mono<String> emptyMono() {
        return Mono.empty();
    }

    // TODO Return an Mono that never emit any signal
    Mono<String> monoWithNoSignal() {
        return Mono.never();
    }

    // TODO Return a Mono that contains a "foo" value
    Mono<String> fooMono() {
        return Mono.just("foo");
    }

    // TODO Create a Mono that emits an IllegalStateException
    Mono<String> errorMono() {
        return Mono.error(new IllegalStateException());
    }
}
