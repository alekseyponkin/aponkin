package ru.job4j.iterator;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Class IteratorEvenNumberTest.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 17.11.2017
 */
public class IteratorEvenNumberTest {
    /**
     * Common IteratorEvenNumber.
     */
    private Iterator<Integer> it;

    /**
     * Create common object IteratorEvenNumber.
     */
    @Before
    public void setUp() {
        it = new IteratorEvenNumber(new int[]{1, 2, 3, 4, 5, 6, 7});
    }

    /**
     * Test should return even number sequentially.
     */
    @Test(expected = NoSuchElementException.class)
    public void shouldReturnEvenNumbersSequentially() {
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(4));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(6));
        assertThat(it.hasNext(), is(false));
        it.next();
    }

    /**
     * Test sequential hashNext invocation doest't affect retrieval order.
     */
    @Test
    public void sequentialHasNextInvocationDoesntAffectRetrievalOrder() {
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(4));
        assertThat(it.next(), is(6));
    }

    /**
     * Test should return false if no any even numbers.
     */
    @Test
    public void  shouldReturnFalseIfNoAnyEvenNumbers() {
        it = new IteratorEvenNumber(new int[]{1});
        assertThat(it.hasNext(), is(false));
    }
}