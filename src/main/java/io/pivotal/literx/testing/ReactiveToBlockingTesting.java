package io.pivotal.literx.testing;

import io.pivotal.literx.domain.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

class ReactiveToBlockingTesting {

    // TODO Return the user contained in that Mono
    User monoToValue(Mono<User> mono) {
        return null;
    }

    // TODO Return the users contained in that Flux
    Iterable<User> fluxToValues(Flux<User> flux) {
        return null;
    }

}
