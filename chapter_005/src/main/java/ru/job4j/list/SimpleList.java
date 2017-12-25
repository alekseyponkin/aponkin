package ru.job4j.list;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.*;

/**
 * Class SimpleList.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 21.11.2017
 */
@ThreadSafe
public class SimpleList<E> implements SimpleContainer<E> {
    /**
     * Store for elements.
     */
    @GuardedBy("this")
    private Object[] list;

    /**
     * Index current
     */
    @GuardedBy("this")
    private int index = 0;

    /**
     * Constructor SimpleList.
     * @param size list size.
     */

    public SimpleList(int size) {
        this.list = new Object[size];
    }

    @Override
    public synchronized void add(E e) {
        if (list.length == index) {
            this.list = Arrays.copyOf(this.list, this.list.length * 2);
            this.list[index++] = e;
        } else {
            this.list[index++] = e;
        }
    }

    @Override
    public synchronized E get(int index) {
        return (E) list[index];
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int indexIterator = 0;
            @Override
            public boolean hasNext() {
                synchronized (this) {
                    return indexIterator < index;
                }
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                synchronized (this) {
                    return (E) list[indexIterator++];
                }
            }
        };
    }
}