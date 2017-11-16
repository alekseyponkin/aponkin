package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class IteratorEvenNumber.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 17.11.2017
 */
public class IteratorEvenNumber implements Iterator {
    /**
     * Accept array[].
     */
    private int[] array;
    /**
     * Iterator index.
     */
    private int index = 0;
    /**
     * Length array.
     */
    private int lengthArray;

    /**
     * Constructor IteratorEvenNumber.
     * @param array processed.
     */
    public IteratorEvenNumber(int[] array) {
        this.array = array;
        this.lengthArray = array.length;
    }

    @Override
    public boolean hasNext() {
        boolean result = false;
        if (getEvenNumber(false) != null) {
            result = true;
        }
        return result;
    }

    @Override
    public Object next() {
        Integer result = getEvenNumber(true);
        if (result == null) {
            throw new NoSuchElementException();
        }
        return result;
    }

    /**
     * Get even number.
     * @param writeIndex true if Next, false if hasNext.
     * @return even number or null - if no any even numbers.
     */
    private Integer getEvenNumber(boolean writeIndex) {
        Integer result = null;
        int indexTemp = this.index;
        for (int i = indexTemp; i < lengthArray; i++) {
            if (array[i] % 2 == 0) {
                result = array[i];
                indexTemp++;
                break;
            }
            indexTemp++;
        }
        if (writeIndex) {
            this.index = indexTemp;
        }
        return result;
    }
}