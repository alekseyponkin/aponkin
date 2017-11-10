package ru.job4j.coffeemachine;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Class Surrender test.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 10.11.2017
 */
public class SurrenderTest {
    /**
     * Test method change then surrender equals fifteen.
     */
    @Test
    public void thenSurrenderFifteen() {
        Surrender surrender = new Surrender();
        int[] result = surrender.changes(50, 35);
        int[] expect = new int[]{10, 5};
        assertThat(result, is(expect));
    }

    /**
     * Test method change then surrender equals seven.
     */
    @Test
    public void thenSurrenderSeven() {
        Surrender surrender = new Surrender();
        int[] result = surrender.changes(42, 35);
        int[] expect = new int[]{5, 2};
        assertThat(result, is(expect));
    }

    /**
     * Test method change then surrender equals three.
     */
    @Test
    public void thenSurrenderThree() {
        Surrender surrender = new Surrender();
        int[] result = surrender.changes(38, 35);
        int[] expect = new int[]{2, 1};
        assertThat(result, is(expect));
    }

    /**
     * Test method change then surrender equals null.
     */
    @Test
    public void thenSurrenderNull() {
        Surrender surrender = new Surrender();
        int[] result = surrender.changes(35, 35);
        int[] expect = new int[]{};
        assertThat(result, is(expect));
    }
}