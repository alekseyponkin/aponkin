package ru.job4j.list;

/**
 * Class SimpleStack.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 21.11.2017
 */
public class SimpleStack<T> extends SimpleLinkedList<T> {
    /**
     * Retrieves end remove element from stack.
     * @return value element.
     */
    public T poll() {
        return super.removeLast();
    }

    /**
     * Push new element in stack.
     * @param value value element.
     */
    public void push(T value) {
        super.add(value);
    }
}