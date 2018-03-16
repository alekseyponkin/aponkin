package ru.job4j.concurrency;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleLockTest {
    private long count = 0;
    private SimpleLock lock = new SimpleLock();
    private int countThreads = 2;

    /**
     * Increment variable without lock.
     */
    private void incrementCount() {
        count++;
    }

    /**
     * Increment and lock variable.
     */
    private void incrementCountLock() {
        lock.lock();
        count++;
        lock.unLock();
    }

    /**
     * Test when counter no lock and test fail.
     * @throws InterruptedException
     */
    @Test
public void whenNoLockAndTestFail() throws InterruptedException {
        Thread[] threads = new Thread[countThreads];
        for (int i = 0; i < countThreads; i++) {
            Runnable runnable = () -> {
                for (int j = 0; j < 1_000_000_000; j++) {
                    incrementCount();
                }
            };
            threads[i] = new Thread(runnable);
            threads[i].start();
        }
        for (int i = 0; i < threads.length; i++) {
            threads[i].join();
        }
        assertFalse(count == (1_00_000_000L * countThreads));
    }

    /**
     * Test when used lock for counter and test successful.
     * @throws InterruptedException
     */
    @Test
    public void whenUseLockAndTestSuccessful() throws InterruptedException {
        Thread[] threads = new Thread[countThreads];
        for (int i = 0; i < countThreads; i++) {
            Runnable runnable = () -> {
                for (int j = 0; j < 10_000_000; j++) {
                    incrementCountLock();
                }
            };
            threads[i] = new Thread(runnable);
            threads[i].start();
        }
        for (int i = 0; i < threads.length; i++) {
            threads[i].join();
        }
        assertThat(count, is(10_000_000L * countThreads));
    }
}