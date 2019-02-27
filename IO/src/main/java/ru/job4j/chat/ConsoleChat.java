package ru.job4j.chat;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Scanner;

/**
 * Class ConsoleChat.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 22.02.2019.
 */
public class ConsoleChat {
    /**
     * Flag enable program.
     */
    private Boolean isEnable = true;
    /**
     * Flag pause program.
     */
    private Boolean isPause = false;
    /**
     * Generator phrase.
     */
    private GeneratorPhrase generatorPhrase;
    /**
     * Properties.
     */
    private Properties properties;

    public static void main(String[] args) throws IOException {
        ConsoleChat chat = new ConsoleChat();
        chat.printTitle();
        chat.start();
        chat.printEnd();
    }

    /**
     * Creates Console chat.
     * @throws IOException
     */
    private ConsoleChat() throws IOException {
        try (InputStream is = ClassLoader.getSystemResourceAsStream("chat/chat.properties")) {
            if (is != null) {
                properties = new Properties();
                properties.load(is);
            } else {
                throw new IOException("File properties not found");
            }
        }
        this.generatorPhrase = new GeneratorPhrase(properties.getProperty("file.phrases"));
    }

    /**
     * Typing the title.
     */
    private void printTitle() {
        System.out.println("==================");
        System.out.println("Start console chat");
        System.out.println("==================");
        System.out.println("'pause' - pause to receive message");
        System.out.println("'continue' - to continue to receive message");
        System.out.println("'exit' - exit chat");
        System.out.println("==================");
        System.out.println("Enter phrases:");
    }

    /**
     * Start program.
     * @throws IOException
     */
    private void start() throws IOException {
        String phrase;
        try (Scanner scanner = new Scanner(System.in);
        LogFile log = LogFile.getInstance(properties.getProperty("file.log"))) {
            while (isEnable) {
                String inWord = scanner.nextLine();
                log.info(inWord);
                switch (inWord) {
                    case "exit" :
                        isEnable = false;
                        isPause = true;
                        break;
                    case "pause" :
                        isPause = true;
                        System.out.println("To continue, enter 'continue'");
                        break;
                    case "continue" : isPause = false;
                    default:
                }
                if (!isPause) {
                    phrase = generatorPhrase.nextPhrase();
                    log.info(phrase);
                    System.out.println(phrase);
                }
            }
        }
    }

    /**
     * Typing end .
     */
    private void printEnd() {
        System.out.println("==================");
        System.out.println("End console chat");
        System.out.println("==================");
    }
}