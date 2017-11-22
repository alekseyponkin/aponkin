package ru.job4j.set;

import ru.job4j.list.SimpleLinkedList;

import java.util.Iterator;

/**
 * Class SimpleSetOnLinkedList.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 23.11.2017
 */
public class SimpleSetOnLinkedList<E> implements Iterable<E> {
    /**
     * Store for elements.
     */
    private SimpleLinkedList<E> set;

    /**
     * Constrictor SimpleSetOnLinkedList.
     */
    public SimpleSetOnLinkedList() {
        this.set = new SimpleLinkedList<>();
    }

    /**
     * Add new element in store.
     * @param e value element.
     */
    public void add(E e) {
        for (E value : this.set) {
            if (value.equals(e)) {
                return;
            }
        }
        this.set.add(e);
    }

    @Override
    public Iterator<E> iterator() {
        return this.set.iterator();
    }
}