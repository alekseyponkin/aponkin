package ru.job4j.generic;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Class SimpleArrayTest.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 20.11.2017
 */
public class SimpleArrayTest {

    /**
     * Test SimpleArray witch Integer.
     */
    @Test
    public void whenTypeIntegerShouldReturnInteger() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(3);
        simpleArray.add(2);
        simpleArray.add(4);
        simpleArray.add(6);
        assertThat(simpleArray.get(0), is(2));
        simpleArray.delete(1);
        assertThat(simpleArray.get(1), is(6));
        simpleArray.update(0, 5);
        assertThat(simpleArray.get(0), is(5));
    }

    /**
     * Test SimpleArray witch String.
     */
    @Test
    public void whenTypeStringShouldReturnString() {
        SimpleArray<String> simpleArray = new SimpleArray<>(3);
        simpleArray.add("One");
        simpleArray.add("Two");
        simpleArray.add("Three");
        assertThat(simpleArray.get(0), is("One"));
        simpleArray.delete(1);
        assertThat(simpleArray.get(1), is("Three"));
        simpleArray.update(0, "Five");
        assertThat(simpleArray.get(0), is("Five"));
    }
}