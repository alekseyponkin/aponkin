package ru.job4j.iterator;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Class MatrixIteratorTest.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 16.11.2017
 */
public class MatrixIteratorTest {
    /**
     * Common IteratorArray.
     */
    private Iterator<Integer> it;

    /**
     * Create common object IteratorArray.
     */
    @Before
    public void setUp() {
        it = new IteratorArray<Integer>(new Integer[][]{{1, 2, 3}, {4, 5, 6}});
    }

    /**
     * Test hasNest next sequential invocation.
     */
    @Test (expected = NoSuchElementException.class)
    public void hasNextNextSequentialInvocation() {
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(3));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(4));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(5));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(6));
        assertThat(it.hasNext(), is(false));
        System.out.println(it.next());
    }

    /**
     * Test that next method  doesn't depends on prior hasNext invocation.
     */
    @Test(expected = NoSuchElementException.class)
    public void testsThatNextMethodDoesntDependsOnPriorHasNextInvocation() {
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(3));
        assertThat(it.next(), is(4));
        assertThat(it.next(), is(5));
        assertThat(it.next(), is(6));
        it.next();
    }

    /**
     * Test sequential hasNext invocation doesn't affect retrieval order.
     */
    @Test(expected = NoSuchElementException.class)
    public void sequentialHasNextInvocationDoesntAffectRetrievalOrder() {
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(3));
        assertThat(it.next(), is(4));
        assertThat(it.next(), is(5));
        assertThat(it.next(), is(6));
        it.next();
    }
}