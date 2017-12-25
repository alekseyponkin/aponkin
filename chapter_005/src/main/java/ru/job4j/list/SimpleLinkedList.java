package ru.job4j.list;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class SimpleLinkedList.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 21.11.2017
 */
@ThreadSafe
public class SimpleLinkedList<E> implements SimpleContainer<E> {
    /**
     * Link on first element.
     */
    @GuardedBy("this")
    Node<E> first;

    /**
     * Link on last element.
     */
    @GuardedBy("this")
    Node<E> last;

    /**
     * Index current.
     */
    @GuardedBy("this")
    private int index = 0;

    @Override
    public synchronized void add(E value) {
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
    public synchronized E get(int index) {
        Node<E> temp = first;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp.item;
    }

    /**
     * Remove first element in store.
     * @return value removed element.
     */
    public synchronized E removeFirst() {
        Node<E> tempFirst = first;
        if (index > 1) {
            first = tempFirst.next;
            first.prev = null;
        } else {
            first = null;
            last = null;
        }
        index--;
        return tempFirst.item;
    }

    /**
     * Remove last element in store.
     * @return value removed element.
     */
    public synchronized E removeLast() {
        Node<E> templast = last;
        if (index > 1) {
            last = last.prev;
            last.next = null;
        } else {
            first = null;
            last = null;
        }
        index--;
        return templast.item;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int indexIterator = 0;
            @Override
            public synchronized boolean hasNext() {
                return indexIterator < index;
            }

            @Override
            public synchronized E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return get(indexIterator++);
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
         * @param prev previous node.
         * @param element value element.
         * @param next next node.
         */
        Node(SimpleLinkedList.Node<E> prev, E element, SimpleLinkedList.Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }
}