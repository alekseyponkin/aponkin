package ru.job4j.list;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class SimpleList.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 21.11.2017
 */
public class SimpleList<E> implements SimpleContainer<E> {
    /**
     * Store for elements.
     */
    private Object[] list;

    /**
     * Index current
     */
    private int index = 0;

    /**
     * Constructor SimpleList.
     * @param size list size.
     */
    public SimpleList(int size) {
        this.list = new Object[size];
    }

    @Override
    public void add(E e) {
        if (list.length == index) {
            this.list = Arrays.copyOf(this.list, this.list.length * 2);
            this.list[index++] = e;
        } else {
            this.list[index++] = e;
        }
    }

    @Override
    public E get(int index) {
        return (E) list[index];
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int indexIterator = 0;
            @Override
            public boolean hasNext() {
                return indexIterator < index ? true : false;
            }

            @Override
            public E next() {
                E result;
                if (indexIterator < index) {
                    result = (E) list[indexIterator++];
                } else {
                    throw new NoSuchElementException();
                }
                return result;
            }
        };
    }
}