package ru.job4j.set;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Class SimpleSetOnLinkedListTest.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 23.11.2017
 */
public class SimpleSetOnLinkedListTest {
    /**
     * Test SimpleSetOnLinkedList witch Integer.
     */
    @Test (expected = NoSuchElementException.class)
    public void whenIntegerUseInSimpleSetOnLinkedListTest() {
        SimpleSetOnLinkedList set = new SimpleSetOnLinkedList();
        set.add(1);
        set.add(3);
        set.add(5);
        set.add(1);
        set.add(5);
        Iterator<Integer> iteratorSet = set.iterator();
        assertTrue(iteratorSet.hasNext());
        assertThat(iteratorSet.next(), is(1));
        assertThat(iteratorSet.next(), is(3));
        assertThat(iteratorSet.next(), is(5));
        assertFalse(iteratorSet.hasNext());
        iteratorSet.next();
    }

    /**
     * Test SimpleSetOnLinkedList witch String.
     */
    @Test (expected = NoSuchElementException.class)
    public void whenStringUseInSimpleSetOnLinkedListTest() {
        SimpleSetOnLinkedList set = new SimpleSetOnLinkedList();
        set.add("2");
        set.add("4");
        set.add("6");
        set.add("6");
        set.add("6");
        Iterator<String> iteratorSet = set.iterator();
        assertTrue(iteratorSet.hasNext());
        assertThat(iteratorSet.next(), is("2"));
        assertThat(iteratorSet.next(), is("4"));
        assertThat(iteratorSet.next(), is("6"));
        assertFalse(iteratorSet.hasNext());
        iteratorSet.next();
    }
}