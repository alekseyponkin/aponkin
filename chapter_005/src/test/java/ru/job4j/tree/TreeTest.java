package ru.job4j.tree;

import org.junit.Test;


import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Class TreeTest.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 29.11.2017
 */
public class TreeTest {

    /**
     * Test when adding Integer in tree successful.
     */
    @Test
    public void whenAddIntegerInTreeSuccessful() {
        Tree<Integer> tree = new Tree<>();
        assertTrue(tree.add(1, 1));
        assertTrue(tree.add(1, 3));
        assertTrue(tree.add(1, 7));
        assertTrue(tree.add(3, 9));
        assertTrue(tree.add(3, 11));
        assertTrue(tree.add(3, 13));
        assertTrue(tree.add(7, 15));
        assertThat(tree.getListValue().toString(), is("[1, 3, 7, 9, 11, 13, 15]"));
    }

    /**
     * Test when adding Integer in tree is fail.
     */
    @Test
    public void whenAddIntegerInTreeFail() {
        Tree<Integer> tree = new Tree<>();
        tree.add(1, 1);
        tree.add(1, 3);
        tree.add(1, 7);
        assertFalse(tree.add(3, 7));
        tree.add(3, 11);
        assertFalse(tree.add(7, 11));
        assertThat(tree.getListValue().toString(), is("[1, 3, 7, 11]"));
    }

    /**
     * Test when adding String in tree is successful.
     */
    @Test
    public void whenAddStringInTreeSuccessful() {
        Tree<String> tree = new Tree<>();
        assertTrue(tree.add("Abc", "Abc"));
        assertTrue(tree.add("Abc", "Bcd"));
        assertTrue(tree.add("Abc", "Cde"));
        assertTrue(tree.add("Cde", "Dej"));
        assertThat(tree.getListValue().toString(), is("[Abc, Bcd, Cde, Dej]"));
    }

    /**
     * Test when adding String in tree is fail.
     */
    @Test
    public void whenAddFailStringInTree() {
        Tree<String> tree = new Tree<>();
        tree.add("Abc", "Abc");
        tree.add("Abc", "Bcd");
        tree.add("Abc", "Cde");
        assertFalse(tree.add("Cde", "Bcd"));
        assertFalse(tree.add("Abc", "Abc"));
        assertThat(tree.getListValue().toString(), is("[Abc, Bcd, Cde]"));
    }

    /**
     * When testing the iterator tree.
     */
    @Test (expected = NoSuchElementException.class)
    public void whenTestIteratorTree() {
        Tree<Integer> tree = new Tree<>();
        tree.add(1, 1);
        tree.add(1, 3);
        tree.add(1, 7);
        tree.add(3, 7);
        tree.add(3, 11);
        tree.add(7, 11);
        Iterator<Integer> iteratorTree = tree.iterator();
        assertTrue(iteratorTree.hasNext());
        assertTrue(iteratorTree.hasNext());
        assertThat(iteratorTree.next(), is(1));
        assertThat(iteratorTree.next(), is(3));
        assertThat(iteratorTree.next(), is(7));
        assertThat(iteratorTree.next(), is(11));
        assertFalse(iteratorTree.hasNext());
        iteratorTree.next();
    }
}