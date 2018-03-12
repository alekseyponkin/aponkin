package ru.job4j.concurrency;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Class SimpleBlockingQueue.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 09.03.2018.
 */
@ThreadSafe
public class SimpleBlockingQueue<T> {
    /**
     * Max size queue.
     */
    private final Integer maxSize;

    /**
     * Size queue.
     */
    @GuardedBy("this")
    private int size = 0;

    /**
     * Queue for storage.
     */
    @GuardedBy("this")
    private Queue<T> queue = new LinkedList<>();

    /**
     * Constructor witch the required size.
     * @param maxSize max size the queue.
     */
    public SimpleBlockingQueue(Integer maxSize) {
        this.maxSize = maxSize;
    }

    /**
     * Add value if queue.
     * @param value value for adding.
     */
    public void offer(T value) {
        synchronized (this) {
            if (size >= maxSize) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            queue.offer(value);
            size++;
            notify();
        }
    }

    /**
     * Retrieves and removes value from the queue.
     * @return value from the queue.
     */
    public T peek() {
        T result;
        synchronized (this) {
           if (size <= 0) {
               try {
                   wait();
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
          result = queue.poll();
          size--;
          notify();
        }
        return result;
    }

    public Queue<T> getQueue() {
        synchronized (this) {
            return queue;
        }
    }
}