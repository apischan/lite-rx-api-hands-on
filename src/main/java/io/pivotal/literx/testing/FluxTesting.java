package io.pivotal.literx.testing;

import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.stream.Stream;

class FluxTesting {

    // TODO Return an empty Flux
    Flux<String> emptyFlux() {
        return Flux.empty();
    }

    // TODO Return a Flux that contains 2 values "foo" and "bar" without using an array or a collection
    Flux<String> fooBarFluxFromValues() {
        return Flux.just("foo", "bar");
    }

    // TODO Create a Flux from a List that contains 2 values "foo" and "bar"
    Flux<String> fooBarFluxFromList() {
        return Flux.fromIterable(Arrays.asList("foo", "bar"));
    }

    // TODO Create a Flux that emits an IllegalStateException
    Flux<String> errorFlux() {
        return Flux.error(new IllegalStateException());
    }

    // TODO Create a Flux that emits increasing values from 0 to 9 each 100ms
    Flux<Long> counter() {
        Stream<Long> longStream = Stream.iterate(0L, i -> i + 1L);
        return Flux.fromStream(longStream)
                .take(10)
                .delayMillis(100L);
    }
}
