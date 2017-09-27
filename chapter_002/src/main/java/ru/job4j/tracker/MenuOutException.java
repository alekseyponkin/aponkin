package ru.job4j.tracker;

/**
 * Class MenuOutException.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 27.09.2017
 */
public class MenuOutException extends RuntimeException {
    /**
     * Constructor MenuOutException exception.
     * @param msg massage exception.
     */
    public MenuOutException(String msg) {
        super(msg);
    }
}
