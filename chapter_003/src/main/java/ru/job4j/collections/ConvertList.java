package ru.job4j.collections;

import java.util.ArrayList;
import java.util.List;

/**
 * Class ConvertList.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 11.11.2017
 */
public class ConvertList {
    /**
     * Convert Array[][] int in List<Integer>.
     * @param array source.
     * @return List<integer>.
     */
    public List<Integer> toList(int[][] array) {
        List<Integer> list = new ArrayList<>();
        for (int[] firs : array) {
            for (int two : firs) {
                list.add(two);
            }
        }
        return list;
    }

    /**
     * Convert List<Integer> in Array[][].
     * @param list source.
     * @param rows count
     * @return Array[][] int.
     */
    public int[][] toArray(List<Integer> list, int rows) {
        int[][] result;
        int indexFirst = 0;
        int indexTwo = 0;
        int countZero = 0;

        if (list.size() % rows == 0) {
            result = new int[list.size() / rows][rows];
        } else {
            result = new int[list.size() / rows + 1][rows];
            countZero = rows - list.size() % rows;
        }

        for (Integer integer : list) {
            result[indexFirst][indexTwo] = integer;
            indexTwo++;
            if (indexTwo == rows) {
                indexFirst++;
                indexTwo = 0;
            }
        }

        for (int i = 0; i < countZero; i++) {
            result[indexFirst][indexTwo] = 0;
            indexTwo++;
        }
        return result;
    }
}
