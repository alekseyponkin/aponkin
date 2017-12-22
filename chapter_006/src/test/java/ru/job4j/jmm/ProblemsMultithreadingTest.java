package ru.job4j.jmm;

import org.junit.Test;

import static org.junit.Assert.assertFalse;

public class ProblemsMultithreadingTest {

    /**
     * Test when ten Thread increment noVolatile variable.
     */
    @Test
    public void whenTenThreadIncrementNoVolatileVariable() throws InterruptedException {
        ProblemsMultithreading problemsMultithreading = new ProblemsMultithreading(100_000_000);
        problemsMultithreading.incrementNoVolatile(10);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertFalse(problemsMultithreading.getNoVolatile() == 1_000_000_000);
    }
}