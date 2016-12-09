package io.pivotal.literx.testing;

import reactor.core.publisher.Mono;

class MonoTesting {
    // TODO Return an empty Mono
    Mono<String> emptyMono() {
        return null;
    }

    // TODO Return an Mono that never emit any signal
    Mono<String> monoWithNoSignal() {
        return null;
    }

    // TODO Return a Mono that contains a "foo" value
    Mono<String> fooMono() {
        return null;
    }

    // TODO Create a Mono that emits an IllegalStateException
    Mono<String> errorMono() {
        return null;
    }
}
