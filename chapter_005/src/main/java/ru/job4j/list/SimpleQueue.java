package ru.job4j.list;

/**
 * Class SimpleQueue.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 22.11.2017
 */
public class SimpleQueue<T> extends SimpleLinkedList<T> {
    /**
     * Retrieves end remove element from queue.
     * @return value element.
     */
    public T poll() {
        return super.removeFirst();
    }

    /**
     * Push new element in queue.
     * @param value value element.
     */
    public void push(T value) {
        super.add(value);
    }
}