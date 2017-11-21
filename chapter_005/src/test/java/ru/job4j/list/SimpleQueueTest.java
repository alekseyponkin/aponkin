package ru.job4j.list;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Class SimpleQueueTest.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 22.11.2017
 */
public class SimpleQueueTest {
    /**
     * Test use SimpleQueue.
     */
    @Test
    public void whenUseSimpleQueue() {
        SimpleQueue<Integer> simpleQueue = new SimpleQueue<>();
        simpleQueue.push(2);
        simpleQueue.push(5);
        simpleQueue.push(7);
        assertThat(simpleQueue.poll(), is(2));
        assertThat(simpleQueue.poll(), is(5));
        assertThat(simpleQueue.poll(), is(7));
    }
}