package ru.job4j.list;

/**
 * Class Node.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 22.11.2017
 */
public class Node<T> {
    /**
     * Value node.
     */
    T value;

    /**
     * Next node.
     */
    Node<T> next;

    /**
     * Constructor Node.
     * @param value value node.
     */
    public Node(T value) {
        this.value = value;
    }

    /**
     * Detection cycle.
     * @param first first node.
     * @return true if a cycle is detected.
     */
    boolean hasCycle(Node<T> first) {
        Node<T> turtle = first;
        Node<T> hare = first.next;
        boolean result = false;
        while (turtle != null && hare.next != null) {
            if (turtle == hare) {
                result = true;
                break;
            }
            turtle = turtle.next;
            hare = hare.next.next;
        }
        return result;
    }
}