package ru.job4j.set;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class SimpleSet.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 22.11.2017
 */
public class SimpleSet<E> implements Iterable<E> {
    /**
     * Store for elements.
     */
    private Object[] set = new Object[100];

    /**
     * Index current
     */
    private int index = 0;

    /**
     * Add new element in store.
     * @param e value element.
     */
    public void add(E e) {
        grow();
        if (!contains(e)) {
            this.set[index++] = e;
        }
    }

    /**
     * Search element in store.
     * @param e element for searching.
     * @return true if element were found.
     */
    public boolean contains(E e) {
        boolean result = false;
        for (int i = 0; i < this.index; i++) {
            if (this.set[i].equals(e)) {
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * Increase size store .
     */
    private void grow() {
        if (this.set.length == this.index) {
            this.set = Arrays.copyOf(this.set, this.set.length * 2);
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int indexIterator = 0;
            @Override
            public boolean hasNext() {
                return indexIterator < index;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (E) set[indexIterator++];
            }
        };
    }
}