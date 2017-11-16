package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class IteratorArray.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 16.11.2017
 */
public class IteratorArray<T> implements Iterator<T> {
    /**
     * Accept array[][].
     */
    private final T[][] array;
    /**
     * Iterator index.
     */
    private int index = 0;
    /**
     * Max index array[][].
     */
    private int maxIndex = 0;

    /**
     * Constructor IteratorArray.
     * @param array processed.
     */
    public IteratorArray(T[][] array) {
        this.array = array;
        for (T[] ts : array) {
                maxIndex += ts.length;
        }
    }

    @Override
    public boolean hasNext() {
        return index < maxIndex;
    }

    @Override
    public T next() {
        T result = null;
        int indexTemp = index;
        for (int i = 0; i < array.length; i++) {
            if (indexTemp < array[i].length) {
                index++;
                result = array[i][indexTemp];
                break;
            }
            indexTemp -= array[i].length;
        }
        if (result == null) {
            throw new NoSuchElementException();
        }
        return result;
    }
}