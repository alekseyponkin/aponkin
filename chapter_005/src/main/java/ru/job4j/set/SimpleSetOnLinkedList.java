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
       if (!contains(e)) {
           this.set.add(e);
       }
    }

    /**
     * Search element in store.
     * @param e element for searching.
     * @return true if element were found.
     */
    public boolean contains(E e) {
        boolean result = false;
        for (E value : this.set) {
            if (value.equals(e)) {
                result = true;
                break;
            }
        }
        return result;
    }
    @Override
    public Iterator<E> iterator() {
        return this.set.iterator();
    }
}