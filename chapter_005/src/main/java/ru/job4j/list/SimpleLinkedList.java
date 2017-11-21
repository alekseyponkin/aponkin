package ru.job4j.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class SimpleLinkedList.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 21.11.2017
 */
public class SimpleLinkedList<E> implements SimpleContainer<E> {
    /**
     * Link on first element.
     */
    Node<E> first;

    /**
     * Link on last element.
     */
    Node<E> last;

    /**
     * Index current.
     */
    private int index = 0;

    @Override
    public void add(E value) {
        Node<E> tempLast = this.last;
        Node<E> newNode = new Node<>(tempLast, value, null);
        this.last = newNode;
        if (index == 0) {
            this.first = newNode;
        } else {
            tempLast.next = newNode;
        }
        index++;
    }

    @Override
    public E get(int index) {
        Node<E> temp = first;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp.item;
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
                if (hasNext()) {
                    result = get(indexIterator++);
                } else {
                    throw new NoSuchElementException();
                }
                return result;
            }
        };
    }

    /**
     * Inner class Node.
     * @param <E> generic.
     */
    private static class Node<E> {
        E item;
        SimpleLinkedList.Node<E> next;
        SimpleLinkedList.Node<E> prev;

        /**
         * Constructor Node.
         * @param prev
         * @param element
         * @param next
         */
        Node(SimpleLinkedList.Node<E> prev, E element, SimpleLinkedList.Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }
}
