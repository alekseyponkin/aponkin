package ru.job4j.tree;

/**
 * Interface SimpleTree.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 29.11.2017
 */
public interface SimpleTree<E extends Comparable<E>> extends Iterable<E> {
    /**
     * Add element child in parent.
     * @param parent parent.
     * @param child child.
     * @return true if add success.
     */
    boolean add(E parent, E child);
}