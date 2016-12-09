package io.pivotal.literx.testing;

import io.pivotal.literx.domain.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

class ErrorsTesting {

    // TODO Return a Mono<User> containing User.SAUL when an error occurs in the input Mono, else do not change the input Mono.
    Mono<User> betterCallSaulForBogusMono(Mono<User> mono) {
        return null;
    }

    // TODO Return a Flux<User> containing User.SAUL and User.JESSE when an error occurs in the input Flux, else do not change the input Flux.
    Flux<User> betterCallSaulAndJesseForBogusFlux(Flux<User> flux) {
        return null;
    }

    // TODO Implement a method that capitalize each user of the incoming flux using the capitalizeUser() method and emit an error containing a GetOutOfHereException exception
    Flux<User> capitalizeMany(Flux<User> flux) {
        return null;
    }

    private User capitalizeUser(User user) throws GetOutOfHereException {
        if (user.equals(User.SAUL)) {
            throw new GetOutOfHereException();
        }
        return new User(user.getUsername(), user.getFirstname(), user.getLastname());
    }

    static class GetOutOfHereException extends Exception {
    }

}
