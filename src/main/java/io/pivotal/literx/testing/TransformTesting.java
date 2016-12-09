package io.pivotal.literx.testing;

import io.pivotal.literx.domain.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

class TransformTesting {

    // TODO Capitalize the user username, firstname and lastname
    Mono<User> capitalizeOne(Mono<User> mono) {
        return null;
    }

    // TODO Capitalize the users username, firstName and lastName
    Flux<User> capitalizeMany(Flux<User> flux) {
        return null;
    }

    // TODO Capitalize the users username, firstName and lastName using asyncCapitalizeUser()
    Flux<User> asyncCapitalizeMany(Flux<User> flux) {
        return null;
    }

    private Mono<User> asyncCapitalizeUser(User u) {
        return Mono.just(new User(u.getUsername().toUpperCase(), u.getFirstname().toUpperCase(), u.getLastname().toUpperCase()));
    }

}
