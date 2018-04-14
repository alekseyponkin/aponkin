package ru.job4j.textsearch;

import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Class ParallelSearchTest.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 17.03.2018.
 */
public class ParallelSearchTest {

    /**
     * Test when found one file.
     */
    @Test
    public void whenSearchOnePathTxtAndOneFileFound() throws URISyntaxException, IOException {
        ParallelSearch parallelSearch  = new ParallelSearch("../chapter_006/target", "Test words", Arrays.asList("txt"));
        List<String> list = parallelSearch.result();
        String expect = Paths.get(ClassLoader.getSystemClassLoader().getResource("TestFile.txt").toURI()).toString();
        assertEquals(expect, list.get(0));
    }

    /**
     * Test when found two files.
     */
    @Test
    public void whenSearchTwoPathTxtJavaAndTwoFileFound() throws URISyntaxException {
        ParallelSearch parallelSearch  = new ParallelSearch("../chapter_006/target", "Test words", Arrays.asList("java", "txt"));
        List<String> list = parallelSearch.result();
        String expect1 = Paths.get(ClassLoader.getSystemClassLoader().getResource("Test.java").toURI()).toString();
        String expect2 = Paths.get(ClassLoader.getSystemClassLoader().getResource("TestFile.txt").toURI()).toString();
        assertEquals(expect1, list.get(0));
        assertEquals(expect2, list.get(1));
    }
}