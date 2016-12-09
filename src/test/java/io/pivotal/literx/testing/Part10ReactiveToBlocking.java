package io.pivotal.literx.testing;

import java.util.Iterator;

import io.pivotal.literx.domain.User;
import io.pivotal.literx.repository.ReactiveRepository;
import io.pivotal.literx.repository.ReactiveUserRepository;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;

import io.pivotal.literx.testing.ReactiveToBlockingTesting;
import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Learn how to turn Reactive API to blocking one.
 *
 * @author Sebastien Deleuze
 */
public class Part10ReactiveToBlocking {

    private ReactiveRepository<User> repository = new ReactiveUserRepository();

    private ReactiveToBlockingTesting reactiveToBlockingTesting = new ReactiveToBlockingTesting();

//========================================================================================

    @Test
    public void mono() {
        Mono<User> mono = repository.findFirst();
        User user = reactiveToBlockingTesting.monoToValue(mono);
        assertEquals(User.SKYLER, user);
    }

//========================================================================================

    @Test
    public void flux() {
        Flux<User> flux = repository.findAll();
        Iterable<User> users = reactiveToBlockingTesting.fluxToValues(flux);
        Iterator<User> it = users.iterator();
        assertEquals(User.SKYLER, it.next());
        assertEquals(User.JESSE, it.next());
        assertEquals(User.WALTER, it.next());
        assertEquals(User.SAUL, it.next());
        assertFalse(it.hasNext());
    }

}
