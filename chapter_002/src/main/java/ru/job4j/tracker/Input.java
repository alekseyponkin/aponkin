package ru.job4j.tracker;

/**
 * Interface Input.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 19.09.2017
 */
public interface Input {
    /**
     * @param question for the user is displayed in console.
     * @return String answer user.
     */
    String ask(String question);
}
