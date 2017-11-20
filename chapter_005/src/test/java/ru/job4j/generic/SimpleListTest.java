package ru.job4j.generic;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Class SimpleListTest.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 20.11.2017
 */
public class SimpleListTest {

    /**
     * Test SimpleList witch Integer.
     */
    @Test
    public void whenTypeIntegerShouldReturnInteger() {
        SimpleList<Integer> simpleList = new SimpleList<>(3);
        simpleList.add(2);
        simpleList.add(4);
        simpleList.add(6);
        assertThat(simpleList.get(0), is(2));
        simpleList.delete(1);
        assertThat(simpleList.get(1), is(6));
        simpleList.update(0, 5);
        assertThat(simpleList.get(0), is(5));
    }

    /**
     * Test SimpleList witch String.
     */
    @Test
    public void whenTypeStringShouldReturnString() {
        SimpleList<String> simpleList = new SimpleList<>(3);
        simpleList.add("One");
        simpleList.add("Two");
        simpleList.add("Three");
        assertThat(simpleList.get(0), is("One"));
        simpleList.delete(1);
        assertThat(simpleList.get(1), is("Three"));
        simpleList.update(0, "Five");
        assertThat(simpleList.get(0), is("Five"));
    }
}