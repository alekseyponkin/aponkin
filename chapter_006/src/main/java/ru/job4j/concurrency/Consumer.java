package ru.job4j.concurrency;

/**
 * Class Consumer.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 10.03.2018.
 */
public class Consumer<T> implements Runnable {
    /**
     * Synchronised queue.
     */
    private SimpleBlockingQueue<T> queue;

    /**
     * Constructor class Consumer.
     * @param queue queue for usage.
     */
    public Consumer(SimpleBlockingQueue<T> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        queue.peek();
    }
}