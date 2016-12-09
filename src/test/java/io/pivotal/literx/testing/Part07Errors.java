/*
 * Copyright 2002-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.pivotal.literx.testing;

import java.util.function.Function;

import io.pivotal.literx.domain.User;
import io.pivotal.literx.testing.ErrorsTesting;
import org.junit.Test;
import reactor.core.Exceptions;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Hooks;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static io.pivotal.literx.testing.ErrorsTesting.*;

/**
 * Learn how to deal with errors.
 *
 * @author Sebastien Deleuze
 * @see Exceptions#propagate(Throwable)
 * @see Hooks#onOperator(Function)
 */
public class Part07Errors {

    private ErrorsTesting errorsTesting = new ErrorsTesting();

//========================================================================================

    @Test
    public void monoWithValueInsteadOfError() {
        Mono<User> mono = errorsTesting.betterCallSaulForBogusMono(Mono.error(new IllegalStateException()));
        StepVerifier.create(mono)
                .expectNext(User.SAUL)
                .expectComplete()
                .verify();

        mono = errorsTesting.betterCallSaulForBogusMono(Mono.just(User.SKYLER));
        StepVerifier.create(mono)
                .expectNext(User.SKYLER)
                .expectComplete()
                .verify();
    }

//========================================================================================

    @Test
    public void fluxWithValueInsteadOfError() {
        Flux<User> flux = errorsTesting.betterCallSaulAndJesseForBogusFlux(Flux.error(new IllegalStateException()));
        StepVerifier.create(flux)
                .expectNext(User.SAUL, User.JESSE)
                .expectComplete()
                .verify();

        flux = errorsTesting.betterCallSaulAndJesseForBogusFlux(Flux.just(User.SKYLER, User.WALTER));
        StepVerifier.create(flux)
                .expectNext(User.SKYLER, User.WALTER)
                .expectComplete()
                .verify();
    }

//========================================================================================

    @Test
    public void handleCheckedExceptions() {
        Flux<User> flux = errorsTesting.capitalizeMany(Flux.just(User.SAUL, User.JESSE));

        StepVerifier.create(flux)
                .expectError(GetOutOfHereException.class)
                .verify();
    }

}
