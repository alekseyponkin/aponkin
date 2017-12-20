package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class Converter.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 19.12.2017.
 */
public class Converter {
    /**
     * Converting the iterator of iterators in common iterator.
     * @param it Iterator of iterators.
     * @return common Iterator.
     */
    Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        return new Iterator<Integer>() {
            /**
             * Inner iterator.
             */
            Iterator<Integer> innerIterator;

            /**
             * Block of initialise anonymous class.
             */
            {
                if (it.hasNext()) {
                    innerIterator = it.next();
                }
            }

            @Override
            public boolean hasNext() {
                if (innerIterator != null && it.hasNext() || innerIterator.hasNext()) {
                    return true;
                }
                return false;
            }

            @Override
            public Integer next() {
                Integer result;
                if (hasNext()) {
                    if (innerIterator.hasNext()) {
                        result = innerIterator.next();
                    } else {
                        innerIterator = it.next();
                        result = innerIterator.next();
                    }
                } else {
                    throw new NoSuchElementException();
                }
                return result;
            }
        };
    }
}