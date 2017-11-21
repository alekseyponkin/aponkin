package ru.job4j.list;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Class SimpleLinkedListTest.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 21.11.2017
 */
public class SimpleLinkedListTest {
    /**
     * Test SimpleLinkedList witch Integer.
     */
    @Test (expected = NoSuchElementException.class)
    public void whenSaveIntegerInSimpleLinkedList() {
        SimpleLinkedList<Integer> list = new SimpleLinkedList<>();
        list.add(2);
        list.add(4);
        list.add(6);
        assertThat(list.get(0), is(2));
        assertThat(list.get(1), is(4));
        assertThat(list.get(2), is(6));
        Iterator<Integer> iterator = list.iterator();
        assertTrue(iterator.hasNext());
        assertTrue(iterator.hasNext());
        assertThat(iterator.next(), is(2));
        assertThat(iterator.next(), is(4));
        assertThat(iterator.next(), is(6));
        assertFalse(iterator.hasNext());
        iterator.next();
    }

    /**
     * Test SimpleLinkedList witch String.
     */
    @Test (expected = NoSuchElementException.class)
    public void whenSaveStringInSimpleLinkedList() {
        SimpleLinkedList<String> list = new SimpleLinkedList<>();
        list.add("1");
        list.add("3");
        list.add("5");
        assertThat(list.get(0), is("1"));
        assertThat(list.get(1), is("3"));
        assertThat(list.get(2), is("5"));
        Iterator<String> iterator = list.iterator();
        assertTrue(iterator.hasNext());
        assertTrue(iterator.hasNext());
        assertThat(iterator.next(), is("1"));
        assertThat(iterator.next(), is("3"));
        assertThat(iterator.next(), is("5"));
        assertFalse(iterator.hasNext());
        iterator.next();
    }
}