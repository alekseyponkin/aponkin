package ru.job4j.list;

/**
 * Interface SimpleContainer .
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 21.11.2017
 */
public interface SimpleContainer<E> extends Iterable<E> {
   /**
    * Add new value.
    * @param value new value for add.
    */
   void add(E value);

   /**
    * Get the value at index.
    * @param index index value.
    * @return value.
    */
   E get(int index);
}