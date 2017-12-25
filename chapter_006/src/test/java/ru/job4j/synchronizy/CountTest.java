package ru.job4j.synchronizy;

import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import  static org.hamcrest.Matchers.is;

public class CountTest {
    /**
     * Inner class describing a thread with a counter.
     */
    private class ThreadCount extends Thread {
        private final Count count;
        /**
         * Constructor class ThreadCount.
         * @param count instance count.
         */
        private ThreadCount(Count count) {
            this.count = count;
        }

        @Override
        public void run() {
            this.count.increment();
        }
    }

    /**
     * Test when execute 2 thread then 2.
     * @throws InterruptedException
     */
    @Test
    public void whenExecute2ThreadThen2() throws InterruptedException {
        final Count count = new Count();
        Thread first = new ThreadCount(count);
        Thread second = new ThreadCount(count);
        first.start();
        second.start();
        first.join();
        second.join();
        assertThat(count.get(), is(2));
    }
}