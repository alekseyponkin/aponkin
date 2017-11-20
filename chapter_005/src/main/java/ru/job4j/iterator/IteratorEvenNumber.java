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
     * Constructor IteratorEvenNumber.
     * @param array processed.
     */
    public IteratorEvenNumber(int[] array) {
        this.array = array;
    }

    @Override
    public boolean hasNext() {
        boolean result = false;
        for (int i = this.index; i < array.length; i++) {
            if (array[i] % 2 == 0) {
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public Object next() {
        Integer result = null;
        for (; this.index < array.length; this.index++) {
            if (array[this.index] % 2 == 0) {
                result = array[this.index];
                this.index++;
                break;
            }
        }
        if (result == null) {
            throw new NoSuchElementException();
        }
        return result;
    }
}