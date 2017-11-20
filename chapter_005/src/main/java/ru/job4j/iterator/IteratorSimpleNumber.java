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
     * Constructor IteratorSimpleNumber.
     * @param numbers processed.
     */
    public IteratorSimpleNumber(final int[] numbers) {
        this.array = numbers;
    }

    @Override
    public boolean hasNext() {
        boolean result = false;
        for (int i = this.index; i < array.length; i++) {
            int intArray = array[i];
            if (checkNumber(intArray)) {
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
            Integer intArray = array[this.index];
            if (checkNumber(intArray)) {
                result = intArray;
                this.index++;
                break;
            }
        }
        if (result == null) {
            throw new NoSuchElementException();
        }
        return result;
    }

    /**
     * Check number simple or not.
     * @param number for check.
     * @return true if number is simple.
     */
    private boolean checkNumber(int number) {
        boolean result = true;
        if (number <= 1) {
            result = false;
        }
        for (int j = 2; j <= number / 2; j++) {
            if (number % j == 0) {
                result = false;
                break;
            }
        }
        return result;
    }
}