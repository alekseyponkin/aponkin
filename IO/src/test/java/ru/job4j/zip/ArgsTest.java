package ru.job4j.zip;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Class CheckStreamTest.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 11.02.2019.
 */
public class ArgsTest {
    private final Args args = new Args(new String[]{"-d", "c:\\project\\job4j\\", "-e", "java,xml", "-o", "project.zip"});

    /**
     * Test when get directory.
     */
    @Test
    public void whenGetDirectory() {
       assertThat(args.getDirectory(), is("c:\\project\\job4j\\"));
    }

    /**
     * Test when get excule extension.
     */
    @Test
    public void whenGetExcule() {
        assertThat(args.getExcule().get(0), is("java"));
        assertThat(args.getExcule().get(1), is("xml"));
    }

    /**
     * Test when get output file.
     */
    @Test
    public void whenGetOutput() {
        assertThat(args.getOutput(), is("project.zip"));
    }
}