package ru.job4j.list;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Class NodeTest.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 22.11.2017
 */
public class NodeTest {
    /**
     * Test when cycle in end of sequence.
     */
    @Test
    public void whenCycleInEndOfSequence() {
        Node<Integer> first = new Node<>(1);
        Node<Integer> two = new Node<>(2);
        Node<Integer> third = new Node<>(3);
        Node<Integer> four = new Node<>(4);
        first.next = two;
        two.next = third;
        third.next = four;
        four.next = first;
        assertTrue(first.hasCycle(first));
    }

    /**
     * Test when cycle in middle of sequence.
     */
    @Test
    public void whenCycleInMiddleOfSequence() {
        Node<Integer> first = new Node<>(2);
        Node<Integer> two = new Node<>(4);
        Node<Integer> third = new Node<>(6);
        Node<Integer> four = new Node<>(8);
        first.next = two;
        two.next = third;
        third.next = two;
        four.next = first;
        assertTrue(first.hasCycle(first));
    }

    /**
     * Test when no cycle in sequence.
     */
    @Test
    public void whenNoCycleInSequence() {
        Node<Integer> first = new Node<>(1);
        Node<Integer> two = new Node<>(3);
        Node<Integer> third = new Node<>(5);
        Node<Integer> four = new Node<>(7);
        first.next = two;
        two.next = third;
        third.next = four;
        assertFalse(first.hasCycle(first));
    }
}