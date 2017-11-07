package ru.job4j.chess;

/**
 * Class figure Elephant.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 25.10.2017
 */
public class Elephant extends Figure {

    /**
     * Constructor class Elephant.
     * @param position initial position.
     */
    public Elephant(Cell position) {
        super(position);
    }

    /**
     * Way figure Elephant.
     * @param dist destination position figure.
     * @return Array cell way.
     * @throws ImpossibleMoveException
     * @throws OccupiedWayException
     * @throws FigureNotFoundException
     */
    @Override
    public Cell[] way(Cell dist) throws ImpossibleMoveException, OccupiedWayException, FigureNotFoundException {
        int thisX = this.getPosition().getX();
        int thisY = this.getPosition().getY();
        int distX = dist.getX();
        int distY = dist.getY();
        int stepX = 0;
        int stepY = 0;
        Cell[] result = new Cell[]{};

        //left-up
        if (distX < thisX && distY > thisY) {
            stepX = -1;
            stepY = 1;
        }

        //right-up
        if (distX > thisX && distY > thisY) {
            stepX = 1;
            stepY = 1;
        }

        //left-down
        if (distX < thisX && distY < thisY) {
            stepX = -1;
            stepY = -1;
        }

        //fight-down
        if (distX > thisX && distY < thisY) {
            stepX = 1;
            stepY = -1;
        }

        if (Math.abs((distX - thisX)) == Math.abs((distY - thisY))) {
            int countStep = Math.abs((distX - thisX));
            Cell[] cells = new Cell[countStep];
            for (int i = 0; i < countStep; i++) {
                thisX += stepX;
                thisY += stepY;
                cells[i] = new Cell(thisX, thisY);
            }
            result = cells;
        }
        return result;
    }

    /**
     * Clone figure Elephant.
     * @param cell new position figure.
     * @return Elephant.
     */
    @Override
    public Figure clone(Cell cell) {
        return new Elephant(cell);
    }

    @Override
    public String toString() {
        return "Elephant{"
                + "position X="
                + position.getX()
                + " position Y="
                + position.getY()
                + '}';
    }
}
