package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class IteratorSimpleNumber.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 17.11.2017
 */
public class IteratorSimpleNumber implements Iterator {
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
     * Constructor IteratorSimpleNumber.
     * @param numbers processed.
     */
    public IteratorSimpleNumber(final int[] numbers) {
        this.array = numbers;
        this.lengthArray = array.length;
    }

    @Override
    public boolean hasNext() {
        boolean result = false;
        if (getSimpleNumber(false) != null) {
            result = true;
        }
        return result;
    }

    @Override
    public Object next() {
        Integer result = getSimpleNumber(true);
        if (result == null) {
            throw new NoSuchElementException();
        }
        return result;
    }

    /**
     * Get simple number.
     * @param writeIndex true if Next, false if hasNext.
     * @return simple number or null - if no any simple numbers.
     */
    private Integer getSimpleNumber(boolean writeIndex) {
        Integer result = null;
        int indexTemp = this.index;
        for (int i = indexTemp; i < lengthArray; i++) {
            int intArray = array[indexTemp];
            Boolean isSimple = true;
            if (intArray <= 1) {
                isSimple = false;
            }
            for (int j = 2; j <= intArray / 2; j++) {
                if (intArray % j == 0) {
                    isSimple = false;
                    break;
                }
            }
            if (isSimple) {
                result = intArray;
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