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

import java.time.Duration;
import java.util.List;

import io.pivotal.literx.domain.User;

import static org.junit.Assert.fail;

import io.pivotal.literx.testing.StepVerifierTesting;
import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Learn how to use StepVerifier to test Mono, Flux or any other kind of Reactive Streams Publisher.
 *
 * @author Sebastien Deleuze
 * @see <a href="http://next.projectreactor.io/ext/docs/api/reactor/test/StepVerifier.html">StepVerifier Javadoc</a>
 */
public class Part03StepVerifier {

    private StepVerifierTesting stepVerifierTesting = new StepVerifierTesting();

//========================================================================================

    @Test
    public void expectElementsThenComplete() {
        stepVerifierTesting.expectFooBarComplete(Flux.just("foo", "bar"));
    }

//========================================================================================

    @Test
    public void expect2ElementsThenError() {
        stepVerifierTesting.expectFooBarError(Flux.just("foo", "bar").concatWith(Mono.error(new RuntimeException())));
    }

//========================================================================================

    @Test
    public void expectElementsWithThenComplete() {
        stepVerifierTesting.expectSkylerJesseComplete(Flux.just(new User("swhite", null, null), new User("jpinkman", null, null)));
    }

//========================================================================================

    @Test
    public void count() {
        stepVerifierTesting.expect10Elements(Flux.interval(Duration.ofSeconds(1)).take(10));
    }

//========================================================================================

    @Test
    public void countWithVirtualTime() {
        stepVerifierTesting.expect3600Elements(
                () -> Flux.interval(Duration.ofMillis(1)).take(5)
        );
    }

}
