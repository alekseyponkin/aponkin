package ru.job4j;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test ArrayDuplicate.
 *
 * @author Ponkin Aleksey (mailto: ponkin.a.v@gmail.com)
 * @version $Id$
 * @since 0.1
 */

public class ContainsWordTest {
    /**
     * Тест метода Contains если слово содержиться в другом.
     */
    @Test
    public void whetOneWordContainOtherWord() {
        ContainsWord containsWord = new ContainsWord();
        String origin = "Привет";
        String sub = "иве";
        boolean result = containsWord.contains(origin, sub);
        assertThat(result, is(true));
    }

    /**
     * Тест метода Contains если слово не содержится в другом.
     */
    @Test
    public void whetOneWordNotContainOtherWord() {
        ContainsWord containsWord = new ContainsWord();
        String origin = "Привет";
        String sub = "Пока";
        boolean result = containsWord.contains(origin, sub);
        assertThat(result, is(false));
    }
}