package ru.job4j.collections;

import com.sun.javafx.binding.StringFormatter;

import java.text.Format;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.TreeSet;

/**
 * Class testing Collections.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 09.11.2017
 */
public class TestList {

    /**
     * Method adding String in collections.
     * @param collection tape collections.
     * @param amount elements count.
     * @return operations time .
     */
    public long add (Collection<String> collection, int amount) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < amount ; i++) {
            collection.add(Integer.toString(i));
        }
        long end = System.currentTimeMillis();
        return end - start;
    }

    /**
     * Method delete String from collections.
     * @param collection tape collections.
     * @param amount elements count.
     * @return operations time .
     */
    public long delete (Collection<String> collection, int amount) {

        for (int i = 0; i < amount ; i++) {
            collection.add(Integer.toString(i));
        }

        long start = System.currentTimeMillis();
        for (int i = 0; i < amount ; i++) {
            collection.remove(Integer.toString(amount - i));
        }
        long end = System.currentTimeMillis();
        return end - start;
    }

    public static void main(String[] args) {
        TestList testList = new TestList();
        int add = 1_000_000;
        int delete = 1_000_000;
        System.out.println(String.format("Add %d string in ArrayList - %d milliseconds", add, testList.add(new ArrayList<String>(), add)));
        System.out.println(String.format("Add %d string in LinkedList - %d milliseconds", add, testList.add(new LinkedList<String>(), add)));
        System.out.println(String.format("Add %d string in TreeSet - %d millisecond", add, testList.add(new TreeSet<String>(), add)));
        System.out.println();
        System.out.println(String.format("Delete %d string in ArrayList - %d milliseconds", delete, testList.delete(new ArrayList<String>(), delete)));
        System.out.println(String.format("Delete %d string in LinkedList - %d milliseconds", delete, testList.delete(new TreeSet<String>(), delete)));
        System.out.println(String.format("Delete %d string in TreeSet - %d milliseconds", delete, testList.delete(new TreeSet<String>(), delete)));
    }
}