package ru.job4j.strategy;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class test pattern strategy.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 23.09.2017
 */
public class PaintTest {
    /**
     * Test paint Triangle.
     */
    @Test
    public void whenPaintTriangle() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        new Paint().draw(new Triangle());
        final String line = System.getProperty("line.separator");
        assertThat(out.toString(), is(String.format("  ^  %s ^^^ %s^^^^^%s", line, line, line)));
    }

    /**
     * Test paint Square.
     */
    @Test
    public void whenPaintSquare() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        new Paint().draw(new Square());
        final String line = System.getProperty("line.separator");
        assertThat(out.toString(), is(String.format("++++%s+  +%s+  +%s++++%s", line, line, line, line)));
    }
}