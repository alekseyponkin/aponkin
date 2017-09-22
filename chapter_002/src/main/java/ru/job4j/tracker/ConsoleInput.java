package ru.job4j.tracker;

import java.util.Scanner;

/**
 * Class ConsoleInput - input user from console.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 19.09.2017
 */
public class ConsoleInput implements Input {

    /**
     * Creating user input console.
     */
    private Scanner scanner = new Scanner(System.in);
    /**
     * @param question for the user is displayed in console.
     * @return String answer user.
     */
    @Override
    public String ask(String question) {
        System.out.println(question);
        return scanner.nextLine();
    }
}
