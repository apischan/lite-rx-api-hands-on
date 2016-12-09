package io.pivotal.literx.testing;

import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

/**
 * Learn how to create Flux instances.
 *
 * @author Sebastien Deleuze
 * @see <a href="http://projectreactor.io/core/docs/api/reactor/core/publisher/Flux.html">Flux Javadoc</a>
 */
public class Part01Flux {

    private FluxTesting fluxTesting = new FluxTesting();

//========================================================================================

    @Test
    public void empty() {
        Flux<String> flux = fluxTesting.emptyFlux();

        StepVerifier.create(flux)
                .expectComplete()
                .verify();
    }

//========================================================================================

    @Test
    public void fromValues() {
        Flux<String> flux = fluxTesting.fooBarFluxFromValues();
        StepVerifier.create(flux)
                .expectNext("foo", "bar")
                .expectComplete()
                .verify();
    }

//========================================================================================

    @Test
    public void fromList() {
        Flux<String> flux = fluxTesting.fooBarFluxFromList();
        StepVerifier.create(flux)
                .expectNext("foo", "bar")
                .expectComplete()
                .verify();
    }

//========================================================================================

    @Test
    public void error() {
        Flux<String> flux = fluxTesting.errorFlux();
        StepVerifier.create(flux)
                .expectError(IllegalStateException.class)
                .verify();
    }

//========================================================================================

    @Test
    public void countEach100ms() {
        Flux<Long> flux = fluxTesting.counter();
        StepVerifier.create(flux)
                .expectNext(0L, 1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L)
                .expectComplete()
                .verify();
    }

}
