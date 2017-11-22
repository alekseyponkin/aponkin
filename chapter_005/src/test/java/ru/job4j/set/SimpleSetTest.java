package ru.job4j.set;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Class SimpleSetTest.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 22.11.2017
 */
public class SimpleSetTest {
    /**
     * Test SimpleSet witch Integer.
     */
    @Test
    public void whenIntegerUseInSimpleSetStore() {
        SimpleSet<Integer> set = new SimpleSet<>();
        set.add(1);
        set.add(3);
        set.add(5);
        set.add(3);
        set.add(5);
        Iterator<Integer> iteratorSet = set.iterator();
        assertTrue(iteratorSet.hasNext());
        assertThat(iteratorSet.next(), is(1));
        assertThat(iteratorSet.next(), is(3));
        assertThat(iteratorSet.next(), is(5));
        assertFalse(iteratorSet.hasNext());
    }

    /**
     * Test SimpleList witch String.
     */
    @Test
    public void whenStringUseInSimpleSetStore() {
        SimpleSet<String> set = new SimpleSet<>();
        set.add("1");
        set.add("3");
        set.add("5");
        set.add("3");
        set.add("5");
        Iterator<String> iteratorSet = set.iterator();
        assertTrue(iteratorSet.hasNext());
        assertThat(iteratorSet.next(), is("1"));
        assertThat(iteratorSet.next(), is("3"));
        assertThat(iteratorSet.next(), is("5"));
        assertFalse(iteratorSet.hasNext());
    }
}