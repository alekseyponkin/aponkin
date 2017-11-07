package ru.job4j.chess;

/**
 * Class FigureNotFoundException.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 25.10.2017
 */
public class FigureNotFoundException extends Exception {
    /**
     * Constructor FigureNotFoundException exception.
     * @param message massage exception.
     */
    public FigureNotFoundException(String message) {
        super(message);
    }
}
