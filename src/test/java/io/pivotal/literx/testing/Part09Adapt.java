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

import java.util.concurrent.CompletableFuture;

import io.pivotal.literx.domain.User;
import io.pivotal.literx.repository.ReactiveRepository;
import io.pivotal.literx.repository.ReactiveUserRepository;
import io.pivotal.literx.testing.AdaptTesting;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;
import org.junit.Test;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

/**
 * Learn how to adapt from/to RxJava 2 Observable/Single/Flowable and Java 8+ CompletableFuture.
 * <p>
 * Mono and Flux already implements Reactive Streams interfaces so they are natively
 * Reactive Streams compliant + there are {@link Mono#from(Publisher)} and {@link Flux#from(Publisher)}
 * factory methods.
 * <p>
 * For RxJava 2, you should not use Reactor Adapter but only RxJava 2 and Reactor Core.
 *
 * @author Sebastien Deleuze
 */
public class Part09Adapt {

    private ReactiveRepository<User> repository = new ReactiveUserRepository();

    private AdaptTesting adaptTesting = new AdaptTesting();

//========================================================================================

    @Test
    public void adaptToFlowable() {
        Flux<User> flux = repository.findAll();
        Flowable<User> observable = adaptTesting.fromFluxToFlowable(flux);
        StepVerifier.create(adaptTesting.fromFlowableToFlux(observable))
                .expectNext(User.SKYLER, User.JESSE, User.WALTER, User.SAUL)
                .expectComplete()
                .verify();
    }

//========================================================================================

    @Test
    public void adaptToObservable() {
        Flux<User> flux = repository.findAll();
        Observable<User> observable = adaptTesting.fromFluxToObservable(flux);
        StepVerifier.create(adaptTesting.fromObservableToFlux(observable))
                .expectNext(User.SKYLER, User.JESSE, User.WALTER, User.SAUL)
                .expectComplete()
                .verify();
    }

//========================================================================================

    @Test
    public void adaptToSingle() {
        Mono<User> mono = repository.findFirst();
        Single<User> single = adaptTesting.fromMonoToSingle(mono);
        StepVerifier.create(adaptTesting.fromSingleToMono(single))
                .expectNext(User.SKYLER)
                .expectComplete()
                .verify();
    }

//========================================================================================

    @Test
    public void adaptToCompletableFuture() {
        Mono<User> mono = repository.findFirst();
        CompletableFuture<User> future = adaptTesting.fromMonoToCompletableFuture(mono);
        StepVerifier.create(adaptTesting.fromCompletableFutureToMono(future))
                .expectNext(User.SKYLER)
                .expectComplete()
                .verify();
    }

}
