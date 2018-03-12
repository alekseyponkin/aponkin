package ru.job4j.concurrency;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleBlockingQueueTest {

    /**
     * Test when one producer adds and one consumer removes.
     */
    @Test
    public void whenOneProducerAddAndOneConsumerRemove() {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(3);
        Producer<Integer> producer = new Producer<>(queue, 3);
        Thread threadProducer = new Thread(producer);
        threadProducer.start();
        try {
            threadProducer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertThat(queue.getQueue().toString(), is("[3]"));
        Consumer<Integer> consumer = new Consumer<>(queue);
        Thread threadConsumer = new Thread(consumer);
        threadConsumer.start();
        try {
            threadConsumer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertThat(queue.getQueue().toString(), is("[]"));
    }

    /**
     * Test when the queue is full and the producer has to wait the consumer.
     */
    @Test
    public void whenQueueFullAndProducerWait() {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(2);
        Producer<Integer> producer = new Producer<>(queue, 3);
        Thread threadProducer = new Thread(producer);
        threadProducer.start();
        Producer<Integer> producer2 = new Producer<>(queue, 2);
        Thread threadProducer2 = new Thread(producer2);
        threadProducer2.start();
        try {
            threadProducer.join();
            threadProducer2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertThat(queue.getQueue().toString(), is("[3, 2]"));
        Producer<Integer> producer3 = new Producer<>(queue, 1);
        Thread threadProducer3 = new Thread(producer3);
        threadProducer3.start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertThat(queue.getQueue().toString(), is("[3, 2]"));
        Consumer<Integer> consumer = new Consumer<>(queue);
        Thread threadConsumer = new Thread(consumer);
        threadConsumer.start();
        try {
            threadProducer3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertThat(queue.getQueue().toString(), is("[2, 1]"));
    }

    /**
     * Test when the queue is empty and the consumer has to wait for the producer.
     */
    @Test
    public void whenQueueEmptyAndConsumerWait() {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(2);
        Consumer<Integer> consumer = new Consumer<>(queue);
        Thread threadConsumer = new Thread(consumer);
        threadConsumer.start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertThat(queue.getQueue().toString(), is("[]"));
        Producer<Integer> producer = new Producer<>(queue, 3);
        Thread threadProducer = new Thread(producer);
        threadProducer.start();
        try {
            threadProducer.join();
            threadConsumer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertThat(queue.getQueue().toString(), is("[]"));
    }
}