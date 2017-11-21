package ru.job4j.list;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Class SimpleStackTest.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 22.11.2017
 */
public class SimpleStackTest {
    /**
     * Test use SimpleStack.
     */
    @Test
    public void whenUseSimpleStack() {
        SimpleStack<Integer> simpleStack = new SimpleStack<>();
        simpleStack.push(2);
        simpleStack.push(5);
        simpleStack.push(7);
        assertThat(simpleStack.poll(), is(7));
        assertThat(simpleStack.poll(), is(5));
        assertThat(simpleStack.poll(), is(2));
    }
}