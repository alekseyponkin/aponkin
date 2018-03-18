package ru.job4j.textsearch;

import org.junit.Test;

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
    public void whenSearchOnePathTxtAndOneFileFound() {
        ParallelSearch parallelSearch  = new ParallelSearch("src/test/java/ru/job4j/textsearch/", "Test words", Arrays.asList("txt"));
        List<String> list = parallelSearch.result();
        assertEquals("src\\test\\java\\ru\\job4j\\textsearch\\TestFile.txt", list.get(0));
    }

    /**
     * Test when found two files.
     */
    @Test
    public void whenSearchTwoPathTxtJavaAndTwoFileFound() {
        ParallelSearch parallelSearch  = new ParallelSearch("src/test/java/ru/job4j/textsearch/", "Test words", Arrays.asList("java", "txt"));
        List<String> list = parallelSearch.result();
        assertEquals("src\\test\\java\\ru\\job4j\\textsearch\\ParallelSearchTest.java", list.get(0));
        assertEquals("src\\test\\java\\ru\\job4j\\textsearch\\TestFile.txt", list.get(1));
    }
}