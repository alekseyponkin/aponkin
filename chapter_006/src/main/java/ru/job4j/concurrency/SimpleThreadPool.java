package ru.job4j.concurrency;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

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
     * The number of working threads.
     */
    private AtomicInteger threedJob = new AtomicInteger(0);
    /**
     * Maximum threads.
     */
    private final int maxThread;
    /**
     * Task to be run.
     */
    private Runnable runnable;

    /**
     * Constructor class SimpleThreadPool.
     */
    public SimpleThreadPool() {
//        this.maxThread = Runtime.getRuntime().availableProcessors();
        this.maxThread = 4;
        for (int i = 0; i < maxThread; i++) {
            new Thread(new Worker()).start();
        }
    }

    /**
     * Add new work to run.
     * @param work
     */
    public void add(Runnable work) {
        if (threedJob.get() <= maxThread && runnable == null) {
            synchronized (queue) {
                this.runnable = work;
                queue.notifyAll();
            }
        } else {
            this.queue.offer(work);
            synchronized (queue) {
                queue.notify();
            }
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
                threedJob.incrementAndGet();
                if (runnable != null) {
                    Runnable tempRunnable;
                    tempRunnable = runnable;
                    runnable = null;
                    tempRunnable.run();
                }
                if (!queue.isEmpty()) {
                    queue.poll().run();
                }
                threedJob.decrementAndGet();
            }
        }
    }

    /**
     * Thread wait if no work.
     */
        private void waitIfNoWork() {
            synchronized (queue) {
                if (runnable == null && queue.isEmpty()) {
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