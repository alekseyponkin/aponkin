package ru.job4j.tree;


import java.util.*;

/**
 * Class Tree.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 29.11.2017
 */
public class Tree<E extends Comparable<E>> implements SimpleTree<E> {
    /**
     * Root tree.
     */
    Node<E> rootTree;

    /**
     * Add element child in parent.
     * @param parent parent.
     * @param child child.
     * @return true if add success.
     */
    @Override
    public boolean add(E parent, E child) {
        boolean result = false;
        Queue<Node<E>> queueNode = new LinkedList<>();
        if (this.isEmpty()) {
            this.rootTree = new Node<E>(child);
            result = true;
        } else if (!this.contains(child)) {
            queueNode.add(this.rootTree);
            while (!queueNode.isEmpty()) {
                Node<E> nodeTemp = queueNode.poll();
                if (nodeTemp.value.compareTo(parent) == 0) {
                        nodeTemp.children.add(new Node<E>(child));
                        result = true;
                        break;
                }
                for (int i = 0; i < nodeTemp.children.size(); i++) {
                    queueNode.add(nodeTemp.children.get(i));
                }
            }
        }
        return result;
    }

    /**
     * Check the tree is binary or not.
     * @return true if binary or false if not.
     */
    public boolean isBinary() {
        boolean result = true;
        if (!this.isEmpty()) {
            Queue<Node<E>> queueNode = new LinkedList<>();
            queueNode.add(rootTree);
            while (!queueNode.isEmpty()) {
                Node<E> node = queueNode.poll();
                if (!(node.children.size() <= 2)) {
                    result = false;
                    break;
                }
                for (int i = 0; i < node.children.size(); i++) {
                    queueNode.add(node.children.get(i));
                }
            }
        }
        return result;
    }

    /**
     * Check that the tree contains value.
     * @param e value.
     * @return true if tree contains value.
     */
    public boolean contains(E e) {
        boolean result = false;
        Queue<Node<E>> queueNode = new LinkedList<>();
        queueNode.add(rootTree);
        while (!queueNode.isEmpty()) {
            Node<E> node = queueNode.poll();
            if (node.value.equals(e)) {
                result = true;
                break;
            }
            for (int i = 0; i < node.children.size(); i++) {
                queueNode.add(node.children.get(i));
            }
        }
        return result;
    }

    /**
     * Check that the tree is empty.
     * @return true if tree is empty.
     */
    public boolean isEmpty() {
        return this.rootTree == null;
    }

    /**
     * Get list value.
     * @return list value.
     */
    public List<E> getListValue() {
        List<E> list = new ArrayList<>();
        Queue<Node<E>> queueNode = new LinkedList<>();
        queueNode.add(rootTree);
        while (!queueNode.isEmpty()) {
            Node<E> node = queueNode.poll();
            list.add(node.value);
            for (int i = 0; i < node.children.size(); i++) {
                queueNode.add(node.children.get(i));
            }
        }
        return list;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            List<E> list = new ArrayList<>();
            Iterator<E> iterator;
            {
                list = getListValue();
                iterator = list.iterator();
            }

            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public E next() {
                return iterator.next();
            }
        };
    }


    /**
     * Inner class Node.
     * @param <E> value.
     */
    public class Node<E> {
        /**
         * List children.
         */
        public List<Node<E>> children;
        /**
         * Value.
         */
        public E value;

        /**
         * Constructor class Node.
         * @param value
         */
        private Node(E value) {
            this.children = new ArrayList<>();
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node<E> node = (Node<E>) o;
            return value != null ? value.equals(node.value) : node.value == null;
        }

        @Override
        public int hashCode() {
            return value != null ? value.hashCode() : 0;
        }
    }
}