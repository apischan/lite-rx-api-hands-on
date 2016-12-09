package io.pivotal.literx.testing;

import io.pivotal.literx.domain.User;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

class RequestTesting {

    // TODO Create a StepVerifier that requests initially all values and expect a 4 values to be received
    StepVerifier requestAllExpectFour(Flux<User> flux) {
        return null;
    }

    // TODO Create a StepVerifier that requests initially 1 value and expects {@link User.SKYLER} then requests another value and expects {@link User.JESSE}.
    StepVerifier requestOneExpectSkylerThenRequestOneExpectJesse(Flux<User> flux) {
        return null;
    }

    // TODO Return a Flux with all users stored in the repository that prints automatically logs for all Reactive Streams signals
    Flux<User> fluxWithLog() {
        return null;
    }

    // TODO Return a Flux with all users stored in the repository that prints "Starring:" on subscribe, "firstname lastname" for all values and "The end!" on complete
    Flux<User> fluxWithDoOnPrintln() {
        return null;
    }

}
