package ru.job4j.generic;

import java.util.Arrays;

/**
 * Class SimpleList.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 20.11.2017
 */
public class SimpleList<T> {
    Object[] objects;
    int index = 0;

    /**
     * Constructor SimpleList.
     * @param size arrays size.
     */
    public SimpleList(int size) {
        this.objects = new Object[size];
    }

    /**
     * Add new element.
     * @param value adding element.
     */
    public void add(T value) {
        if (objects.length == index) {
            this.objects = Arrays.copyOf(this.objects, this.objects.length * 2);
            this.objects[index] = value;
            index++;
        } else {
            this.objects[index] = value;
            index++;
        }
    }

    /**
     * Update element.
     * @param index element for update.
     * @param value updating element.
     */
    public void update(int index, T value) {
        objects[index] = value;
    }

    /**
     * Delete element.
     * @param position deleting element.
     */
    public void delete(int position) {
        int numMoved = this.objects.length - position - 1;
        System.arraycopy(this.objects, position + 1, this.objects, position, numMoved);
        index--;
    }

    /**
     * Get element.
     * @param positon geting element.
     * @return value element.
     */
    public T get(int positon) {
        return (T) this.objects[positon];
    }
}