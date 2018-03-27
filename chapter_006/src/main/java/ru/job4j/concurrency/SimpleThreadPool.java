package ru.job4j.concurrency;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Class SimpleThreadPool.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 12.03.2018.
 */
public class SimpleThreadPool {
    /**
     * The queue for threads.
     */
    private Queue<Runnable> queue = new LinkedBlockingQueue<>();
    /**
     * Maximum threads.
     */
    private final int maxThread;

    /**
     * Constructor class SimpleThreadPool.
     */
    public SimpleThreadPool() {
        this.maxThread = Runtime.getRuntime().availableProcessors();
        for (int i = 0; i < maxThread; i++) {
            new Thread(new Worker()).start();
        }
    }

    /**
     * Add new work to run.
     * @param work for run.
     */
    public void add(Runnable work) {
        this.queue.offer(work);
        synchronized (queue) {
            queue.notify();
        }
        System.out.println(queue.toString());
    }

    /**
     * Inner class Worker.
     */
    private class Worker implements Runnable {
        @Override
        public void run() {
            while (true) {
                waitIfNoWork();
                if (!queue.isEmpty()) {
                    queue.poll().run();
                }
            }
        }
    }

    /**
     * Thread wait if no work.
     */
        private void waitIfNoWork() {
            synchronized (queue) {
                if (queue.isEmpty()) {
                    try {
                        queue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    public static void main(String[] args) {
        SimpleThreadPool pool = new SimpleThreadPool();
        for (int i = 0; i < 7; i++) {
            Runnable runnable = () -> {
                System.out.println(Thread.currentThread().getName() + " Start");
                for (int j = 0; j < 10; j++) {
                    System.out.println(Thread.currentThread().getName() + " j = " + j);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName() + " Finish");
            };
            pool.add(runnable);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}