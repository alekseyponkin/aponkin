package ru.job4j.set;

import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Class SimpleSetOnHashTableTest.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 23.11.2017
 */
public class SimpleSetOnHashTableTest {
    /**
     * Test SimpleSetOnHashTable witch Integer.
     */
    @Test (expected = NoSuchElementException.class)
    public void whenIntegerUseInSimpleSetOnHashTable() {
        SimpleSetOnHashTable<Integer> hastTable = new SimpleSetOnHashTable<>(4);
        hastTable.add(1);
        assertTrue(hastTable.add(3));
        assertFalse(hastTable.add(5));
        assertTrue(hastTable.add(8));
        assertThat(Arrays.toString(hastTable.hashTable), is("[8, 1, null, 3]"));
        hastTable.grow();
        hastTable.add(5);
        assertThat(Arrays.toString(hastTable.hashTable), is("[8, 1, null, 3, null, 5, null, null]"));
        assertTrue(hastTable.contains(5));
        assertTrue(hastTable.remove(5));
        Iterator<Integer> hashIterator = hastTable.iterator();
        assertTrue(hashIterator.hasNext());
        assertThat(hashIterator.next(), is(8));
        assertThat(hashIterator.next(), is(1));
        assertThat(hashIterator.next(), is(3));
        assertFalse(hashIterator.hasNext());
        hashIterator.next();
    }

    /**
     * Test SimpleSetOnHashTable witch String.
     */
    @Test (expected = NoSuchElementException.class)
    public void whenStringUseInSimpleSetOnHashTable() {
        SimpleSetOnHashTable<String> hastTable = new SimpleSetOnHashTable<>(4);
        hastTable.add("Abc");
        assertTrue(hastTable.add("Bcd"));
        assertTrue(hastTable.add("Cde"));
        assertTrue(hastTable.contains("Cde"));
        assertThat(Arrays.toString(hastTable.hashTable), is("[Cde, null, Abc, Bcd]"));
        Iterator<String> hashIterator = hastTable.iterator();
        assertTrue(hashIterator.hasNext());
        assertThat(hashIterator.next(), is("Cde"));
        assertThat(hashIterator.next(), is("Abc"));
        assertThat(hashIterator.next(), is("Bcd"));
        assertFalse(hashIterator.hasNext());
        hashIterator.next();
    }
}