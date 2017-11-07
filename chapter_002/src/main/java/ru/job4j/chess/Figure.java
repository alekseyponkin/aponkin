package ru.job4j.chess;

/**
 * Abstract class Figure.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 25.10.2017
 */
public abstract class Figure {
    /**
     * Position figure.
     */
    final Cell position;

    /**
     * Constructor Figure.
     * @param position initial position figure.
     */
    public Figure(Cell position) {
        this.position = position;
    }

    /**
     * Abstract method way figure.
     * @param dist destination position figure.
     * @return Array cell way.
     * @throws ImpossibleMoveException
     * @throws OccupiedWayException
     * @throws FigureNotFoundException
     */
    public  abstract Cell[] way(Cell dist) throws ImpossibleMoveException, OccupiedWayException, FigureNotFoundException;

    /**
     * Abstract method clone figure.
     * @param cell new position figure.
     * @return figure.
     */
    public abstract Figure clone(Cell cell);

    public Cell getPosition() {
        return position;
    }
}
