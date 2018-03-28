package ru.job4j.bomberman;

/**
 * Class Cell.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 26.03.2018.
 */
public class Cell {
    /**
     * Column board.
     */
    private final int x;
    /**
     * Row board.
     */
    private final int y;

    /**
     * Constructor class Cell.
     * @param x initial column.
     * @param y inital row.
     */
    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "Cell{"
                + "x="
                + x
                + ", y="
                + y
                + '}';
    }

    @Override
    public boolean equals(Object obj) {
        boolean result;
        Cell cell = (Cell) obj;
        if (this.x == cell.getX() && this.y == cell.getY()) {
            result = true;
        } else {
            result = false;
        }
        return result;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}