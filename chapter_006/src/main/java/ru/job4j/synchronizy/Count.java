package ru.job4j.synchronizy;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * Class Count.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 22.12.2017.
 */
@ThreadSafe
public class Count {
    /**
     * Value for increment.
     */
    @GuardedBy("this")
    private int value;
    /**
     * Increment value.
     */
    public synchronized void increment() {
        this.value++;
    }

    public synchronized int get() {
        return this.value;
    }
}