package ru.job4j.concurrency;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * Class SimpleLock.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 15.03.2018.
 */
@ThreadSafe
public class SimpleLock {
    /**
     * Boolean variable for blocking.
     */
    @GuardedBy("this")
    private Boolean lockThread = false;

    /**
     * Enable lock.
     */
    public void lock() {
        synchronized (this) {
            while (lockThread) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            lockThread = true;
        }
    }

    /**
     * Disable lock.
     */
    public void unLock() {
        synchronized (this) {
            if (lockThread) {
                lockThread = false;
                this.notify();
            }
        }
    }
}