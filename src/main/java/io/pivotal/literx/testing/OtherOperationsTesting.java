package io.pivotal.literx.testing;

import io.pivotal.literx.domain.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

class OtherOperationsTesting {

    // TODO Create a Flux of user from Flux of username, firstname and lastname.
    Flux<User> userFluxFromStringFlux(Flux<String> usernameFlux, Flux<String> firstnameFlux, Flux<String> lastnameFlux) {
        return null;
    }

    // TODO return the mono which returns faster its value
    Mono<User> useFastestMono(Mono<User> mono1, Mono<User> mono2) {
        return null;
    }

    // TODO return the flux which returns faster the first value
    Flux<User> useFastestFlux(Flux<User> flux1, Flux<User> flux2) {
        return null;
    }

    // TODO Convert the input Flux<User> to a Mono<Void> that represents the complete signal of the flux
    Mono<Void> fluxCompletion(Flux<User> flux) {
        return null;
    }

    // TODO Return a valid Mono of user for null input and non null input user (hint: Reactive Streams does not accept null values)
    Mono<User> nullAwareUserToMono(User user) {
        return null;
    }

    // TODO Return the same mono passed as input parameter, expect that it will emit User.SKYLER when empty
    Mono<User> emptyToSkyler(Mono<User> mono) {
        return null;
    }
}
