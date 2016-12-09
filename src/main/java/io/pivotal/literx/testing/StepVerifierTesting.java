package io.pivotal.literx.testing;

import io.pivotal.literx.domain.User;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.test.scheduler.VirtualTimeScheduler;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

class StepVerifierTesting {

    // TODO Use StepVerifier to check that the flux parameter emits "foo" and "bar" elements then completes successfully.
    void expectFooBarComplete(Flux<String> flux) {
        StepVerifier.create(flux)
                .expectNext("foo", "bar")
                .expectComplete()
                .verify();
    }

    // TODO Use StepVerifier to check that the flux parameter emits "foo" and "bar" elements then a RuntimeException error.
    void expectFooBarError(Flux<String> flux) {
        StepVerifier.create(flux)
                .expectNext("foo", "bar")
                .expectError(RuntimeException.class)
                .verify();
    }

    // TODO Use StepVerifier to check that the flux parameter emits a User with "swhite" username and another one with "jpinkman" then completes successfully.
    void expectSkylerJesseComplete(Flux<User> flux) {
        StepVerifier.create(flux)
                .expectNextMatches(u -> "swhite".equals(u.getUsername()))
                .expectNextMatches(u -> "jpinkman".equals(u.getUsername()))
                .expectComplete()
                .verify();
    }

    // TODO Expect 10 elements then complete and notice how long it takes for running the test
    void expect10Elements(Flux<Long> flux) {
        Duration length = StepVerifier.create(flux)
                .expectNextCount(10)
                .expectComplete()
                .verify();

        System.out.println(length.getSeconds());
    }

    // TODO Expect 3600 elements then complete using the virtual time capabilities provided via StepVerifier.withVirtualTime() and notice how long it takes for running the test
    void expect3600Elements(Supplier<Flux<Long>> supplier) {
        throw new UnsupportedOperationException();
    }

}
