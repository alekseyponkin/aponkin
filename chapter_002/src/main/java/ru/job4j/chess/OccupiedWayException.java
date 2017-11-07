package ru.job4j.chess;

/**
 * Class OccupiedWayException.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 25.10.2017
 */
public class OccupiedWayException extends Exception {
    /**
     * Constructor OccupiedWayException.
     * @param message massage exception.
     */
    public OccupiedWayException(String message) {
        super(message);
    }
}
