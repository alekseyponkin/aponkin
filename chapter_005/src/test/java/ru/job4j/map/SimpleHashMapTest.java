package ru.job4j.map;

import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

/**
 * Class SimpleHashMapTest.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 28.11.2017
 */
public class SimpleHashMapTest {
    /**
     * Test when insert Integer key in SimpleHashMap.
     */
    @Test
    public void whenInsertIntegerInSimpleHashMap() {
        SimpleHashMap<Integer, Object> simpleHashMap = new SimpleHashMap<>(4);
        simpleHashMap.insert(1, new Object());
        simpleHashMap.insert(3, new Object());
        assertFalse(simpleHashMap.insert(5, new Object()));
        assertTrue(simpleHashMap.insert(8, new Object()));
        assertThat(Arrays.toString(simpleHashMap.hashTable), is("[Key=8, Key=1, null, Key=3]"));
    }

    /**
     * Test when SimpleHashMap grow.
     */
    @Test
    public void whenSimpleHashMapGrow() {
        SimpleHashMap<Integer, Object> simpleHashMap = new SimpleHashMap<>(4);
        simpleHashMap.insert(1, new Object());
        simpleHashMap.insert(3, new Object());
        assertFalse(simpleHashMap.insert(5, new Object()));
        simpleHashMap.insert(8, new Object());
        assertThat(Arrays.toString(simpleHashMap.hashTable), is("[Key=8, Key=1, null, Key=3]"));
        simpleHashMap.grow();
        assertTrue(simpleHashMap.insert(5, new Object()));
        assertThat(Arrays.toString(simpleHashMap.hashTable), is("[Key=8, Key=1, null, Key=3, null, Key=5, null, null]"));
    }

    /**
     * Test when delete element from SimpleHashMap.
     */
    @Test
    public void whenDeleteElementFromSimpleHashMap() {
        SimpleHashMap<Integer, Object> simpleHashMap = new SimpleHashMap<>(4);
        simpleHashMap.insert(1, new Object());
        simpleHashMap.insert(3, new Object());
        simpleHashMap.insert(8, new Object());
        assertThat(Arrays.toString(simpleHashMap.hashTable), is("[Key=8, Key=1, null, Key=3]"));
        assertTrue(simpleHashMap.delete(1));
        assertThat(Arrays.toString(simpleHashMap.hashTable), is("[Key=8, null, null, Key=3]"));
    }

    /**
     * Test get value by key string from SimpleHashMap.
     */
    @Test
    public void whenGetValueByKeyStingFromSimpleHashMap() {
        SimpleHashMap<String, Object> simpleHashMap = new SimpleHashMap<>(4);
        simpleHashMap.insert("One", new Object());
        Object object = new Object();
        simpleHashMap.insert("Two", object);
        simpleHashMap.insert("Three", new Object());
        assertThat(simpleHashMap.get("Two"), is(object));
    }

    /**
     * Test iterator SimpleHashMap.
     */
    @Test (expected = NoSuchElementException.class)
    public void whenTestIteratorSimpleHashMap() {
        SimpleHashMap<Integer, Object> simpleHashMap = new SimpleHashMap<>(4);
        simpleHashMap.insert(1, new Object());
        simpleHashMap.insert(8, new Object());
        simpleHashMap.insert(3, new Object());
        Iterator<Integer> iterator = simpleHashMap.iterator();
        assertTrue(iterator.hasNext());
        assertTrue(iterator.hasNext());
        assertThat(iterator.next(), is(8));
        assertThat(iterator.next(), is(1));
        assertThat(iterator.next(), is(3));
        assertFalse(iterator.hasNext());
        iterator.next();
    }
}