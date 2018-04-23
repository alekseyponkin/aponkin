package ru.job4j.optimization;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.hamcrest.Matchers.is;

/**
 * Class OptimizationTest.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 22.04.2018.
 */
public class OptimizationTest {
    Optimization optimization = new Optimization();

    /**
     * Test when optimization with one thousand N then check time executing.
     */
    @Test
    public void whenOptimizationWithOneThousandNThenCheckTimeExecuting() {
        Long start = System.currentTimeMillis();
        optimization.setDbName("./src/main/java/ru/job4j/optimization/optimization.db");
        optimization.setN(1_000);
        optimization.start();
        Long timeJob = System.currentTimeMillis() - start;
        int expectedTimeJob = 300_000;
        assertTrue(timeJob < expectedTimeJob);
    }

    /**
     * Test when optimization with one thousand N then check sum N.
     */
    @Test
    public void whenOptimizationWithOneThousandNThenCheckSumN() {
        optimization.setDbName("./src/main/java/ru/job4j/optimization/optimization.db");
        optimization.setN(1_000);
        Long result = optimization.start();
        Long expcted = 500500L;
        assertThat(result, is(expcted));
    }
}