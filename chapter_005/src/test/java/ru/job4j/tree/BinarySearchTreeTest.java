package ru.job4j.tree;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class BinarySearchTreeTest.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 05.12.2017
 */
public class BinarySearchTreeTest {
    /**
     * Test add element in Binary search tree.
     */
    @Test
    public void whenTestingAddInBinarySearchTree() {
        BinarySearchTree binarySearchTree = new BinarySearchTree();
        binarySearchTree.add(9);
        binarySearchTree.add(15);
        binarySearchTree.add(19);
        binarySearchTree.add(11);
        binarySearchTree.add(5);
        binarySearchTree.add(7);
        binarySearchTree.add(5);
        binarySearchTree.add(3);
        assertThat(binarySearchTree.rootTree.value, is(9));
        assertThat(binarySearchTree.rootTree.left.value, is(5));
        assertThat(binarySearchTree.rootTree.left.left.value, is(5));
        assertThat(binarySearchTree.rootTree.left.right.value, is(7));
        assertThat(binarySearchTree.rootTree.left.left.left.value, is(3));
        assertThat(binarySearchTree.rootTree.right.value, is(15));
        assertThat(binarySearchTree.rootTree.right.left.value, is(11));
        assertThat(binarySearchTree.rootTree.right.right.value, is(19));
    }
}