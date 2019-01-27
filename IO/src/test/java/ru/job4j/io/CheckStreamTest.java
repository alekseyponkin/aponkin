package ru.job4j.io;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.util.InputMismatchException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Class CheckStreamTest.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 22.01.2018.
 */
public class CheckStreamTest {

    /**
     * Test when enter even number.
     */
    @Test
    public void whenEnterEvenNumber() {
        CheckStream checkStream = new CheckStream();
        assertTrue(checkStream.isNumber(new ByteArrayInputStream("32".getBytes())));
    }

    /**
     * Test when enter negative even number.
     */
    @Test
    public void whenEnterNegativeEvenNumber() {
        CheckStream checkStream = new CheckStream();
        assertTrue(checkStream.isNumber(new ByteArrayInputStream("-4".getBytes())));
    }

    /**
     * Test when enter uneven number.
     */
    @Test
    public void whenEnterUnevenNumber() {
        CheckStream checkStream = new CheckStream();
        assertFalse(checkStream.isNumber(new ByteArrayInputStream("333".getBytes())));
    }

    /**
     * Test when enter string.
     */
    @Test (expected = InputMismatchException.class)
    public void whenEnterString() {
        CheckStream checkStream = new CheckStream();
        assertTrue(checkStream.isNumber(new ByteArrayInputStream("test".getBytes())));
    }
}