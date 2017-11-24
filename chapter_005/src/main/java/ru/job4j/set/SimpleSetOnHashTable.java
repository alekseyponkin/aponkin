package ru.job4j.set;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class SimpleSetOnHashTable.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 23.11.2017
 */
public class SimpleSetOnHashTable<E> implements Iterable<E> {
    /**
     * Store for elements.
     */
    Object[] hashTable;
    /**
     * Size store.
     */
    private int size;

    /**
     * Constrictor SimpleSetOnHashTable.
     */
    public SimpleSetOnHashTable(int size) {
        this.hashTable = new Object[size];
        this.size = size;
    }
    /**
     * Add new element in store.
     * @param e value element.
     * @return true if element was added.
     */
    public boolean add(E e) {
        boolean result = false;
        int positionAdd = Math.abs(e.hashCode()) % this.size;
        if (this.hashTable[positionAdd] == null) {
            this.hashTable[positionAdd] = e;
            result = true;
        }
        return result;
    }

    /**
     * Search element in store.
      @param e element for searching.
     * @return true if element were found.
     */
    public boolean contains(E e) {
        boolean result = false;
        for (Object o : this.hashTable) {
            if (o != null && o.equals(e)) {
                result = true;
            }
        }
        return result;
    }

    /**
     * Remove element from store.
     * @param e element for removing.
     * @return true if element was removed.
     */
    public boolean remove(E e) {
        boolean result = false;
        for (int i = 0; i < this.hashTable.length; i++) {
            if (e.equals((E) hashTable[i])) {
                this.hashTable[i] = null;
                result = true;
            }
        }
        return result;
    }

    /**
     * Increase in store size.
     */
    public void grow() {
        SimpleSetOnHashTable<E> newHashTable = new SimpleSetOnHashTable<>(this.size * 2);
        for (Object o : this.hashTable) {
            if (o != null) {
                newHashTable.add((E) o);
            }
        }
        this.hashTable =  newHashTable.toArray();
        this.size = hashTable.length;
    }

    /**
     * Get array store.
     * @return array Object store.
     */
    private Object[] toArray() {
        return this.hashTable;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int indexIterator = 0;
            @Override
            public boolean hasNext() {
                return indexOfNotNull() >= 0;
            }

            @Override
            public E next() {
                int index = indexOfNotNull();
                if (index >= 0) {
                    indexIterator = index + 1;
                    return (E) hashTable[index];
                }
                throw new NoSuchElementException();
            }

            /**
             * Find index next not null element.
             * @return -1 if not find, >= 0 if element was found.
             */
            private int indexOfNotNull() {
                int result = -1;
                for (int i = indexIterator; i < hashTable.length; i++) {
                    if (hashTable[i] != null) {
                        result = i;
                        break;
                    }
                }
                return result;
            }
        };
    }
}