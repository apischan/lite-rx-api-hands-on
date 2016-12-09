package io.pivotal.literx.testing;

import io.pivotal.literx.domain.User;
import io.pivotal.literx.repository.ReactiveRepository;
import io.pivotal.literx.repository.ReactiveUserRepository;
import io.pivotal.literx.testing.OtherOperationsTesting;
import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

/**
 * Learn how to use various other operators.
 *
 * @author Sebastien Deleuze
 */
public class Part08OtherOperations {

    private final static User MARIE = new User("mschrader", "Marie", "Schrader");
    private final static User MIKE = new User("mehrmantraut", "Mike", "Ehrmantraut");

    private OtherOperationsTesting otherOperationsTesting = new OtherOperationsTesting();

//========================================================================================

    @Test
    public void zipFirstNameAndLastName() {
        Flux<String> usernameFlux = Flux.just(User.SKYLER.getUsername(), User.JESSE.getUsername(), User.WALTER.getUsername(), User.SAUL.getUsername());
        Flux<String> firstnameFlux = Flux.just(User.SKYLER.getFirstname(), User.JESSE.getFirstname(), User.WALTER.getFirstname(), User.SAUL.getFirstname());
        Flux<String> lastnameFlux = Flux.just(User.SKYLER.getLastname(), User.JESSE.getLastname(), User.WALTER.getLastname(), User.SAUL.getLastname());
        Flux<User> userFlux = otherOperationsTesting.userFluxFromStringFlux(usernameFlux, firstnameFlux, lastnameFlux);
        StepVerifier.create(userFlux)
                .expectNext(User.SKYLER, User.JESSE, User.WALTER, User.SAUL)
                .expectComplete()
                .verify();
    }

//========================================================================================

    @Test
    public void fastestMono() {
        ReactiveRepository<User> repository1 = new ReactiveUserRepository(MARIE);
        ReactiveRepository<User> repository2 = new ReactiveUserRepository(250, MIKE);
        Mono<User> mono = otherOperationsTesting.useFastestMono(repository1.findFirst(), repository2.findFirst());
        StepVerifier.create(mono)
                .expectNext(MARIE)
                .expectComplete()
                .verify();

        repository1 = new ReactiveUserRepository(250, MARIE);
        repository2 = new ReactiveUserRepository(MIKE);
        mono = otherOperationsTesting.useFastestMono(repository1.findFirst(), repository2.findFirst());
        StepVerifier.create(mono)
                .expectNext(MIKE)
                .expectComplete()
                .verify();
    }

//========================================================================================

    @Test
    public void fastestFlux() {
        ReactiveRepository<User> repository1 = new ReactiveUserRepository(MARIE, MIKE);
        ReactiveRepository<User> repository2 = new ReactiveUserRepository(250);
        Flux<User> flux = otherOperationsTesting.useFastestFlux(repository1.findAll(), repository2.findAll());
        StepVerifier.create(flux)
                .expectNext(MARIE, MIKE)
                .expectComplete()
                .verify();

        repository1 = new ReactiveUserRepository(250, MARIE, MIKE);
        repository2 = new ReactiveUserRepository();
        flux = otherOperationsTesting.useFastestFlux(repository1.findAll(), repository2.findAll());
        StepVerifier.create(flux)
                .expectNext(User.SKYLER, User.JESSE, User.WALTER, User.SAUL)
                .expectComplete()
                .verify();
    }

//========================================================================================

    @Test
    public void complete() {
        ReactiveRepository<User> repository = new ReactiveUserRepository();
        Mono<Void> completion = otherOperationsTesting.fluxCompletion(repository.findAll());
        StepVerifier.create(completion)
                .expectComplete()
                .verify();
    }

//========================================================================================

    @Test
    public void nullHandling() {
        Mono<User> mono = otherOperationsTesting.nullAwareUserToMono(User.SKYLER);
        StepVerifier.create(mono)
                .expectNext(User.SKYLER)
                .expectComplete()
                .verify();
        mono = otherOperationsTesting.nullAwareUserToMono(null);
        StepVerifier.create(mono)
                .expectComplete()
                .verify();
    }

//========================================================================================

    @Test
    public void emptyHandling() {
        Mono<User> mono = otherOperationsTesting.emptyToSkyler(Mono.just(User.WALTER));
        StepVerifier.create(mono)
                .expectNext(User.WALTER)
                .expectComplete()
                .verify();
        mono = otherOperationsTesting.emptyToSkyler(Mono.empty());
        StepVerifier.create(mono)
                .expectNext(User.SKYLER)
                .expectComplete()
                .verify();
    }

}
