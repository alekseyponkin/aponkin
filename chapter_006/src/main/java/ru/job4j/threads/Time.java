package ru.job4j.threads;

/**
 * Class Time.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 21.12.2017.
 */
public class Time implements Runnable {
    /**
     * Thread for control.
     */
    private Thread thread;
    /**
     * Maximum execution time.
     */
    private long timeMax;

    /**
     * Constructor class Time.
     * @param runnable class which realise interface Runnable.
     * @param timeMax maximum execution time.
     */
    public Time(Runnable runnable, long timeMax) {
        this.thread = new Thread(runnable);
        this.timeMax = timeMax;
    }

    @Override
    public void run() {
        long stastTime = System.currentTimeMillis();
        thread.start();
        long finishTime = System.currentTimeMillis() - stastTime;
        while (finishTime <= this.timeMax) {
            finishTime = System.currentTimeMillis() - stastTime;
        }
        this.thread.interrupt();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String text = "Test  simple   text where 25 char";
        Thread threadTemer = new Thread(new Time(new CountChar(text), 10));
        System.out.println("Program to counting the char.");
        threadTemer.start();
        try {
            threadTemer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("End program.");
        System.out.println("===================================");

        System.out.println();
        System.out.println("Program to counting the char.");
        threadTemer = new Thread(new Time(new CountChar(text), 100));
        threadTemer.start();
        try {
            threadTemer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("End program.");
        System.out.println("===================================");
    }
}