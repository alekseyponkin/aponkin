package ru.job4j.tree;

/**
 * Class BinarySearchTree.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 05.12.2017
 */
public class BinarySearchTree<E extends Comparable<E>> {
    /**
     * Root tree.
     */
    Node<E> rootTree;

    /**
     * Add element child in parent.
     * @param e element for adding.
     */
    public void add(E e) {
        if (this.isEmpty()) {
            rootTree = new Node<>(e);
        } else {
            findPlaces(rootTree, e);
        }
    }

    /**
     * Find places for add element.
     * @param node the node where to look places.
     * @param e element for searching.
     */
    public void findPlaces(Node<E> node, E e) {
        if (node.value.compareTo(e) >= 0) {
            if (node.left == null) {
                node.left = new Node<>(e);
            } else {
                this.findPlaces(node.left, e);
            }
        } else {
            if (node.right == null) {
                node.right = new Node<>(e);
            } else {
                this.findPlaces(node.right, e);
            }
        }
    }

    /**
     * Check that the tree is empty.
     * @return true if tree is empty.
     */
    public boolean isEmpty() {
        return this.rootTree == null;
    }

    /**
     * Inner class Node.
     * @param <E> value.
     */
    public class Node<E> {
        /**
         * Value.
         */
        public E value;
        /**
         * Left node.
         */
        public Node<E> left;
        /**
         * Right node.
         */
        public Node<E> right;

        /**
         * Constructor class Node.
         * @param value
         */
        private Node(E value) {
            this.value = value;
        }
    }
}