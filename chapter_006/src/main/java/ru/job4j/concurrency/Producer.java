package ru.job4j.concurrency;

/**
 * Class Producer.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 10.03.2018.
 */
public class Producer<T> implements Runnable {
    /**
     * Synchronised queue.
     */
    private SimpleBlockingQueue<T> queue;

    /**
     * The value for adding in queue.
     */
    private T value;

    /**
     * Constructor class Producer.
     * @param queue queue for usage.
     */
    public Producer(SimpleBlockingQueue<T> queue, T value) {
        this.queue = queue;
        this.value = value;
    }

    @Override
    public void run() {
        queue.offer(this.value);
    }
}