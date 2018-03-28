package ru.job4j.bomberman;

import java.util.Random;

/**
 * Class Bomberman.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 26.03.2018.
 */
public class Bomberman implements Runnable {
    /**
     * Flag that bomberman is live.
     */
    private boolean live = true;
    /**
     * Cell position bomberman.
     */
    private Cell cell;
    /**
     * Game board.
     */
    private final GameBoard gameBoard;

    /**
     * Constructor bomberman.
     * @param gameBoard the mame board.
     * @param cell the initial cell.
     */
    public Bomberman(GameBoard gameBoard, Cell cell) {
        this.gameBoard = gameBoard;
        this.cell = cell;
    }

    @Override
    public void run() {
        gameBoard.lockCell(cell);
        Cell randomCell;
        while (live) {
            do {
               randomCell = randomWay();
            } while (!gameBoard.moveUnit(cell, randomCell));
            this.cell = randomCell;
            System.out.println(Thread.currentThread().getName() + " " + cell);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Random way.
     * @return the cell destination.
     */
    private Cell randomWay() {
        Cell result = null;
        Random random = new Random();
        switch (random.nextInt(4)) {
            case (0):
                result = new Cell(this.cell.getX(), this.cell.getY() - 1);
                break;
            case (1):
                result = new Cell(this.cell.getX(), this.cell.getY() + 1);
                break;
            case (2):
                result = new Cell(this.cell.getX() - 1, this.cell.getY());
                break;
            case (3):
                result = new Cell(this.cell.getX() + 1, this.cell.getY());
                break;
            default:
        }
        return result;
    }
}