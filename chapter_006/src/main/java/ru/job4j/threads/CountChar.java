package ru.job4j.threads;

/**
 * Class CountChar.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 21.12.2017.
 */
public class CountChar implements Runnable {
    /**
     * Text for searching.
     */
    private String text;

    /**
     * Constructor class CountChar.
     * @param text text for searching.
     */
    public CountChar(String text) {
        this.text = text;
    }

    @Override
    public void run() {
        System.out.println("Tread count char is started.");
        int countChar = 0;
        for (int i = 0; i < text.length(); i++) {
            if (!Thread.currentThread().isInterrupted()) {
                if (text.charAt(i) != ' ') {
                    countChar++;
                }
            } else {
                System.out.println("The program was stopped. Exceeded run time.");
                return;
            }
        }
        System.out.println("Char: " + countChar);
    }
}