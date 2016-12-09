package io.pivotal.literx.testing;

import io.pivotal.literx.domain.User;
import io.pivotal.literx.repository.ReactiveRepository;
import io.pivotal.literx.repository.ReactiveUserRepository;
import io.pivotal.literx.testing.RequestTesting;
import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

/**
 * Learn how to control the demand.
 *
 * @author Sebastien Deleuze
 */
public class Part06Request {

    private ReactiveRepository<User> repository = new ReactiveUserRepository();

    private RequestTesting requestTesting = new RequestTesting();

//========================================================================================

    @Test
    public void requestAll() {
        Flux<User> flux = repository.findAll();
        StepVerifier verifier = requestTesting.requestAllExpectFour(flux);
        verifier.verify();
    }

//========================================================================================

    @Test
    public void requestOneByOne() {
        Flux<User> flux = repository.findAll();
        StepVerifier verifier = requestTesting.requestOneExpectSkylerThenRequestOneExpectJesse(flux);
        verifier.verify();
    }

//========================================================================================

    @Test
    public void experimentWithLog() {
        Flux<User> flux = requestTesting.fluxWithLog();
        StepVerifier.create(flux, 0)
                .thenRequest(1)
                .expectNextMatches(u -> true)
                .thenRequest(1)
                .expectNextMatches(u -> true)
                .thenRequest(2)
                .expectNextMatches(u -> true)
                .expectNextMatches(u -> true)
                .expectComplete()
                .verify();
    }


//========================================================================================

    @Test
    public void experimentWithDoOn() {
        Flux<User> flux = requestTesting.fluxWithDoOnPrintln();
        StepVerifier.create(flux)
                .expectNextCount(4)
                .expectComplete()
                .verify();
    }

}
