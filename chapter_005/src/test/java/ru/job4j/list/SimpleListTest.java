package ru.job4j.list;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Class SimpleListTest.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 21.11.2017
 */
public class SimpleListTest {

    /**
     * Test SimpleList witch Integer.
     */
    @Test (expected = NoSuchElementException.class)
    public void whenUseIntegerInSimpleList() {
        SimpleList<Integer> simpleList = new SimpleList<>(2);
        simpleList.add(2);
        simpleList.add(4);
        simpleList.add(6);
        simpleList.add(8);
        assertThat(simpleList.get(3), is(8));
        Iterator<Integer> iterator = simpleList.iterator();
        assertTrue(iterator.hasNext());
        assertTrue(iterator.hasNext());
        assertTrue(iterator.hasNext());
        assertThat(iterator.next(), is(2));
        assertThat(iterator.next(), is(4));
        assertThat(iterator.next(), is(6));
        assertThat(iterator.next(), is(8));
        iterator.next();
    }

    /**
     * Test SimpleList witch String.
     */
    @Test (expected = NoSuchElementException.class)
    public void whenUseStringInSimpleList() {
        SimpleList<String> simpleList = new SimpleList<>(2);
        simpleList.add("1");
        simpleList.add("3");
        simpleList.add("5");
        simpleList.add("7");
        assertThat(simpleList.get(3), is("7"));
        Iterator<String> iterator = simpleList.iterator();
        assertTrue(iterator.hasNext());
        assertTrue(iterator.hasNext());
        assertTrue(iterator.hasNext());
        assertThat(iterator.next(), is("1"));
        assertThat(iterator.next(), is("3"));
        assertThat(iterator.next(), is("5"));
        assertThat(iterator.next(), is("7"));
        iterator.next();
    }
}