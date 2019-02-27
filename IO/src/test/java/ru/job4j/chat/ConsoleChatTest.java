package ru.job4j.chat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Class ConsoleChatTest.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 26.02.2019.
 */
public class ConsoleChatTest {
    private static final String LINE_SEPARATOR = System.getProperty("line.separator");
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    /**
     * Test program Console chat.
     * @throws IOException
     */
    @Test
    public void whenRunMainMethod() throws IOException {
        ByteArrayInputStream in = new ByteArrayInputStream((""
                + "test" + LINE_SEPARATOR
                + "pause" + LINE_SEPARATOR
                + "test" + LINE_SEPARATOR
                + "continue" + LINE_SEPARATOR
                + "exit" + LINE_SEPARATOR).getBytes());
        System.setIn(in);
        ConsoleChat.main(new String[]{});
        String result = new StringBuilder()
                .append("==================").append(LINE_SEPARATOR)
                .append("Start console chat").append(LINE_SEPARATOR)
                .append("==================").append(LINE_SEPARATOR)
                .append("'pause' - pause to receive message").append(LINE_SEPARATOR)
                .append("'continue' - to continue to receive message").append(LINE_SEPARATOR)
                .append("'exit' - exit chat").append(LINE_SEPARATOR)
                .append("==================").append(LINE_SEPARATOR)
                .append("Enter phrases:").append(LINE_SEPARATOR)
                .append("phrase one").append(LINE_SEPARATOR)
                .append("To continue, enter 'continue'").append(LINE_SEPARATOR)
                .append("phrase one").append(LINE_SEPARATOR)
                .append("==================").append(LINE_SEPARATOR)
                .append("End console chat").append(LINE_SEPARATOR)
                .append("==================").append(LINE_SEPARATOR).toString();
        assertThat(outContent.toString(), is(result));
    }

    @After
    public void restoreStreamsAndRemovePackageZip() {
        System.setOut(originalOut);
        System.setIn(System.in);
    }
}