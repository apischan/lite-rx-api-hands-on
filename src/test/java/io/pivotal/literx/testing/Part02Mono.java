package io.pivotal.literx.testing;

import java.time.Duration;

import io.pivotal.literx.testing.MonoTesting;
import org.junit.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

/**
 * Learn how to create Mono instances.
 *
 * @author Sebastien Deleuze
 * @see <a href="http://projectreactor.io/core/docs/api/reactor/core/publisher/Mono.html">Mono Javadoc</a>
 */
public class Part02Mono {

    private MonoTesting monoTesting = new MonoTesting();

//========================================================================================

    @Test
    public void empty() {
        Mono<String> mono = monoTesting.emptyMono();
        StepVerifier.create(mono)
                .expectComplete()
                .verify();
    }

//========================================================================================

    @Test
    public void noSignal() {
        Mono<String> mono = monoTesting.monoWithNoSignal();
        StepVerifier
                .create(mono)
                .expectSubscription()
                .expectNoEvent(Duration.ofSeconds(1))
                .thenCancel()
                .verify();
    }

//========================================================================================

    @Test
    public void fromValue() {
        Mono<String> mono = monoTesting.fooMono();
        StepVerifier.create(mono)
                .expectNext("foo")
                .expectComplete()
                .verify();
    }

//========================================================================================

    @Test
    public void error() {
        Mono<String> mono = monoTesting.errorMono();
        StepVerifier.create(mono)
                .expectError(IllegalStateException.class)
                .verify();
    }

}
