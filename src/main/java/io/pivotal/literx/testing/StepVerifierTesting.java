package io.pivotal.literx.testing;

import io.pivotal.literx.domain.User;
import reactor.core.publisher.Flux;

import java.util.function.Supplier;

class StepVerifierTesting {

    // TODO Use StepVerifier to check that the flux parameter emits "foo" and "bar" elements then completes successfully.
    void expectFooBarComplete(Flux<String> flux) {
        throw new UnsupportedOperationException();
    }

    // TODO Use StepVerifier to check that the flux parameter emits "foo" and "bar" elements then a RuntimeException error.
    void expectFooBarError(Flux<String> flux) {
        throw new UnsupportedOperationException();
    }

    // TODO Use StepVerifier to check that the flux parameter emits a User with "swhite" username and another one with "jpinkman" then completes successfully.
    void expectSkylerJesseComplete(Flux<User> flux) {
        throw new UnsupportedOperationException();
    }

    // TODO Expect 10 elements then complete and notice how long it takes for running the test
    void expect10Elements(Flux<Long> flux) {
        throw new UnsupportedOperationException();
    }

    // TODO Expect 3600 elements then complete using the virtual time capabilities provided via StepVerifier.withVirtualTime() and notice how long it takes for running the test
    void expect3600Elements(Supplier<Flux<Long>> supplier) {
        throw new UnsupportedOperationException();
    }

}
