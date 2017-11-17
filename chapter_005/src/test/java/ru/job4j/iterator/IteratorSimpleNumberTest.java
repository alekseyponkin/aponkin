package ru.job4j.iterator;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Class IteratorSimpleNumberTest.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 17.11.2017
 */
public class IteratorSimpleNumberTest {
    /**
     * Common IteratorEvenNumber.
     */
    private Iterator<Integer> it;

    /**
     * Create common object IteratorEvenNumber.
     */
    @Before
    public void setUp() {
        it = new IteratorSimpleNumber(new int[]{1, 2, 3, 4, 5, 6, 7, 3571});
    }

    /**
     * Test should return simple numbers only.
     */
    @Test(expected = NoSuchElementException.class)
    public void shouldReturnSimpleNumbersOnly() {
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(3));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(5));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(7));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(3571));
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
        assertThat(it.next(), is(3));
        assertThat(it.next(), is(5));
        assertThat(it.next(), is(7));
        assertThat(it.next(), is(3571));
    }

    /**
     * Test should return false cause there is no any simple numbers.
     */
    @Test
    public void shouldReturnFalseCauseThereIsNoAnySimpleNumber() {
        it = new IteratorSimpleNumber(new int[]{4, 6});
        assertThat("should return false, cause there is no any prime number", it.hasNext(), is(false));
    }
}