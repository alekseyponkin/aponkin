package ru.job4j.io;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Class RemoveForbiddenWordsTest.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 23.01.2019.
 */
public class RemoveForbiddenWordsTest {

    /**
     * Test when contains one abuse word in text.
     */
    @Test
    public void whenTextContainsOneAbuseWord() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream("Test text with one abuse word".getBytes());
        String[] abuse = new String[] {"abuse"};
        new RemoveForbiddenWords().dropAbuse(in, out, abuse);
        assertThat(out.toString(), is("Test text with one word"));
    }

    /**
     * Test when contains two abuse word in text.
     */
    @Test
    public void whenTextContainsTwoAbuseWord() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream("Test text with one abuse and one bad word".getBytes());
        String[] abuse = new String[] {"abuse", "bad"};
        new RemoveForbiddenWords().dropAbuse(in, out, abuse);
        assertThat(out.toString(), is("Test text with one and one word"));
    }

    /**
     * Test when contains two abuse word in text with punctuation char.
     */
    @Test
    public void whenTextContainsTwoAbuseWordAndPunctuationChar() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
    ByteArrayInputStream in = new ByteArrayInputStream("Test text with one abuse and one bad word!".getBytes());
        String[] abuse = new String[] {"abuse", "bad"};
        new RemoveForbiddenWords().dropAbuse(in, out, abuse);
        assertThat(out.toString(), is("Test text with one and one word!"));
    }
}