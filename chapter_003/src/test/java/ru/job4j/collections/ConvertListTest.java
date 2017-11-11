package ru.job4j.collections;


import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;


/**
 * Class ConvertListTest.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 11.11.2017
 */
public class ConvertListTest {
    @Test
    public void thenConvertToList() throws Exception {
        ConvertList convertList = new ConvertList();
        List<Integer> result = convertList.toList(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
        List<Integer> expect = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        assertThat(result, is(expect));
    }

    @Test
    public void thenMultipleRowsConvertToArray() throws Exception {
        ConvertList convertList = new ConvertList();
        int[][] result = convertList.toArray(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9), 3);
        int[][] expect = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        assertThat(result, is(expect));
    }

    @Test
    public void thenNotMultipleRowsConvertToArray() throws Exception {
        ConvertList convertList = new ConvertList();
        int[][] result = convertList.toArray(Arrays.asList(1, 2, 3, 4, 5, 6, 7), 3);
        int[][] expect = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 0, 0}};
        assertThat(result, is(expect));
    }
}