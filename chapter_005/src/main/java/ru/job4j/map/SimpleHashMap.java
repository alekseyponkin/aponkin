package ru.job4j.map;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class SimpleHashMap.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 27.11.2017
 */
public class SimpleHashMap<K, V> implements Iterable<K> {
    /**
     * Store for elements.
     */
    Node[] hashTable;

    /**
     * Size store.
     */
    private int size;

    /**
     * Constrictor SimpleSetOnHashTable.
     */
    public SimpleHashMap(int size) {
        this.hashTable = new Node[size];
        this.size = size;
    }

    /**
     * Insert new key end value in store.
     * @param key insert key.
     * @param value insert value.
     * @return true if element was inserted.
     */
    public boolean insert(K key, V value) {
        boolean result = false;
        int positionAdd = getPositionHash(key);
        if (this.hashTable[positionAdd] == null) {
            this.hashTable[positionAdd] = new Node(key, value);
            result = true;
        }
        return result;
    }

    /**
     * Get value by key.
     * @param key key.
     * @return value.
     */
    public V get(K key) {
        V result = null;
        if (contains(key)) {
            result = (V) hashTable[getPositionHash(key)].value;
        }
        return result;
    }

    /**
     * Delete key end value from store.
     * @param key for deleting.
     * @return true if key end value was deleted.
     */
    public boolean delete(K key) {
        boolean result = false;
        if (contains(key)) {
            this.hashTable[getPositionHash(key)] = null;
            result = true;
        }
        return result;
    }

    /**
     * Search key in store.
     * @param key key for searching.
     * @return true if element were found.
     */
    public boolean contains(K key) {
        boolean result = false;
         Node node = this.hashTable[getPositionHash(key)];
        if (node != null && node.key.equals(key)) {
            result = true;
        }
        return result;
    }

    /**
     * Increase in store size.
     */
    public void grow() {
        Node[] newHashTable = new Node[this.size * 2];
        for (Node node : this.hashTable) {
            if (node != null) {
                newHashTable[getPositionHash((K) node.key)] = node;
            }
        }
        this.hashTable = newHashTable;
        this.size = hashTable.length;
    }

    /**
     * Get position element Hash.
     * @param key key.
     * @return index position in hashTable
     */
    private int getPositionHash(K key) {
        return Math.abs(key.hashCode()) % this.size;
    }


    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            int indexIterator = 0;
            @Override
            public boolean hasNext() {
                return indexOfNotNull() >= 0;
            }

            @Override
            public K next() {
                int index = indexOfNotNull();
                if (index >= 0) {
                    indexIterator = index + 1;
                    return (K) hashTable[index].key;
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

    /**
     * Inner class Node.
     * @param <K> key.
     * @param <V> value.
     */
    static class Node<K, V> {
        K key;
        V value;

        /**
         * Constructor class Node.
         * @param key key.
         * @param value value.
         */
        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public final String toString() {
            return "Key" + "=" + key;
        }
    }
}