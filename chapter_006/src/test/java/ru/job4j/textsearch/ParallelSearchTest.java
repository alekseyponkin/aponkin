package ru.job4j.textsearch;

import org.junit.Test;

import java.io.File;
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
        String separator = File.separator;
        String expect = String.format("src%stest%1$sjava%1$sru%1$sjob4j%1$stextsearch%1$sTestFile.txt", separator);
        assertEquals(expect, list.get(0));
    }

    /**
     * Test when found two files.
     */
    @Test
    public void whenSearchTwoPathTxtJavaAndTwoFileFound() {
        ParallelSearch parallelSearch  = new ParallelSearch("src/test/java/ru/job4j/textsearch/", "Test words", Arrays.asList("java", "txt"));
        List<String> list = parallelSearch.result();
        String separator = File.separator;
        String expect1 = String.format("src%stest%1$sjava%1$sru%1$sjob4j%1$stextsearch%1$sParallelSearchTest.java", separator);
        String expect2 = String.format("src%stest%1$sjava%1$sru%1$sjob4j%1$stextsearch%1$sTestFile.txt", separator);
        assertEquals(expect1, list.get(0));
        assertEquals(expect2, list.get(1));
    }
}