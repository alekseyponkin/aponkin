package ru.job4j.threads;

/**
 * Class CounterWords.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 15.12.2017.
 */
public class CounterWords implements Runnable {
    /**
     * Text for searching.
     */
    private String text;

    /**
     * Constructor class CounterWords.
     * @param text text for searching.
     */
    public CounterWords(String text) {
        this.text = text;
    }

    @Override
    public void run() {
            System.out.println("Tread count word is started.");
            int countWord = 0;
            for (int i = 0; i < text.length(); i++) {
                if (text.charAt(i) != ' ') {
                    while (i < text.length() && text.charAt(i) != ' ') {
                        i++;
                    }
                    countWord++;
                }
            }
            System.out.println("Word : " + countWord);
    }

    public static void main(String[] args) {
        String text = "Test  simple   text where 9 word end 11 space";
        Thread threadCountWords = new Thread(new CounterWords(text));
        Thread threadCountSpace = new Thread(() -> {
            System.out.println("Tread count space is started.");
            int countSpace = 0;
            for (int i = 0; i < text.length(); i++) {
                if (text.charAt(i) == ' ') {
                    countSpace++;
                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Space : " + countSpace);
        });
        System.out.println("Program to counting the words and spaces.");
        threadCountSpace.start();
        threadCountWords.start();
        try {
            threadCountWords.join();
            threadCountSpace.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("End program.");
    }
}