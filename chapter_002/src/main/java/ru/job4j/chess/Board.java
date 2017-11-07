package ru.job4j.chess;

/**
 * Class Board.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 25.10.2017
 */

public class Board {

    /**
     * Array figure.
     */
    private Figure[] figures = new Figure[32];

    /**
     * @param source current position figure.
     * @param dist destination position figure.
     * @return true if move possible.
     * @throws ImpossibleMoveException
     * @throws OccupiedWayException
     * @throws FigureNotFoundException
     */
    public boolean move(Cell source, Cell dist) throws ImpossibleMoveException, OccupiedWayException, FigureNotFoundException {
        int indexFigure = findIndexFigure(source);
        if (indexFigure == -1) {
            throw new FigureNotFoundException("Figure not found");
        }
        Cell[] cells = figures[indexFigure].way(dist);
        if (cells.length == 0) {
            throw new ImpossibleMoveException("Figure can't go");
        }
        if (!checkWay(cells)) {
            throw  new OccupiedWayException("Way busy other figure");
        }
        figures[indexFigure] = figures[indexFigure].clone(dist);
        return true;
    }

    /**
     * Check way.
     * @param cells way.
     * @return true if way free.
     */
    private boolean checkWay(Cell[] cells) {
        boolean result = true;
        for (Cell cell: cells) {
            for (Figure figure: figures) {
                if (figure != null) {
                    if (figure.getPosition().equals(cell)) {
                        result = false;
                        break;
                    }
                }
            }
        }
        return result;
    }

    /**
     * Find index figure located in the cell.
     * @param cell source figure.
     * @return
     */
    private int findIndexFigure(Cell cell) {
        int result = -1;
        for (int i = 0; i < 32; i++) {
            if (figures[i] != null) {
                if (figures[i].getPosition().equals(cell)) {
                    result = i;
                    break;
                }
            }
        }
        return result;
    }

    public Figure[] getFigures() {
        return figures;
    }
}
