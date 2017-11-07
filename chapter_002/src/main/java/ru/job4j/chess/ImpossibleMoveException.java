package ru.job4j.chess;

/**
 * Class ImpossibleMoveException.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 25.10.2017
 */
public class ImpossibleMoveException extends Exception {
    /**
     * Constructor ImpossibleMoveException exception.
     * @param message massage exception.
     */
    public ImpossibleMoveException(String message) {
        super(message);
    }
}
