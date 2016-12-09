package io.pivotal.literx.testing;

import io.pivotal.literx.domain.User;
import io.pivotal.literx.repository.ReactiveRepository;
import io.pivotal.literx.repository.ReactiveUserRepository;
import io.pivotal.literx.testing.TransformTesting;
import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

/**
 * Learn how to transform values.
 *
 * @author Sebastien Deleuze
 */
public class Part04Transform {

    private ReactiveRepository<User> repository = new ReactiveUserRepository();

    private TransformTesting transformTesting = new TransformTesting();

//========================================================================================

    @Test
    public void transformMono() {
        Mono<User> mono = repository.findFirst();
        StepVerifier.create(transformTesting.capitalizeOne(mono))
                .expectNext(new User("SWHITE", "SKYLER", "WHITE"))
                .expectComplete()
                .verify();
    }

//========================================================================================

    @Test
    public void transformFlux() {
        Flux<User> flux = repository.findAll();
        StepVerifier.create(transformTesting.capitalizeMany(flux))
                .expectNext(
                        new User("SWHITE", "SKYLER", "WHITE"),
                        new User("JPINKMAN", "JESSE", "PINKMAN"),
                        new User("WWHITE", "WALTER", "WHITE"),
                        new User("SGOODMAN", "SAUL", "GOODMAN"))
                .expectComplete()
                .verify();
    }

//========================================================================================

    @Test
    public void asyncTransformFlux() {
        Flux<User> flux = repository.findAll();
        StepVerifier.create(transformTesting.asyncCapitalizeMany(flux))
                .expectNext(
                        new User("SWHITE", "SKYLER", "WHITE"),
                        new User("JPINKMAN", "JESSE", "PINKMAN"),
                        new User("WWHITE", "WALTER", "WHITE"),
                        new User("SGOODMAN", "SAUL", "GOODMAN"))
                .expectComplete()
                .verify();
    }

}
