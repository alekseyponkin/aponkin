package ru.job4j.jmm;

/**
 * Class ProblemsMultithreading.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 22.12.2017.
 */
public class ProblemsMultithreading {
    /**
     * Count increment.
     */
    private final int countIncrement;
    /**
     * Variable for increment.
     */
    private long noVolatile = 0;

    /**
     * Constructor class ProblemsMultithreading.
     * @param countIncrement count increment no volatile variable.
     */
    public ProblemsMultithreading(int countIncrement) {
        this.countIncrement = countIncrement;
    }

    /**
     * Method increment no volatile variable.
     * @param countThread count thread to increment variable.
     */
    public void incrementNoVolatile(int countThread) {
        for (int i = 0; i < countThread; i++) {
            Runnable runnable = () -> {
                for (int i1 = 0; i1 < countIncrement; i1++) {
                    noVolatile++;
                }
                System.out.println(noVolatile + " " + Thread.currentThread().getName());
            };
            new Thread(runnable).start();
        }
    }

    public long getNoVolatile() {
        return noVolatile;
    }
}