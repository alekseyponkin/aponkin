package ru.job4j.bomberman;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Class GameBoard.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 26.03.2018.
 */
public class GameBoard {
    /**
     * Max column board.
     */
    private final int maxX;
    /**
     * Max row board.
     */
    private final int maxY;
    /**
     * Array ReentrantLock.
     */
    private final ReentrantLock[][] board;

    /**
     * Constructor the board initialising.
     * @param maxX max column on board.
     * @param maxY max row on board.
     */
    public GameBoard(int maxX, int maxY) {
        this.maxX = maxX;
        this.maxY = maxY;
        this.board = new ReentrantLock[maxX][maxY];
        for (int i = 0; i < maxX; i++) {
            for (int j = 0; j < maxY; j++) {
               this.board[i][j] = new ReentrantLock();
            }
        }
    }

    /**
     * Move the unit on cell.
     * @param currentPosition current position unit.
     * @param destinationPosition destination position unit.
     * @return {@code true} if moveUnit successful, and {@code false} otherwise.
     */
    public boolean moveUnit(Cell currentPosition, Cell destinationPosition) {
        boolean result = false;
        try {
            if (checkWay(destinationPosition) && this.board[destinationPosition.getX()][destinationPosition.getY()].tryLock(500, TimeUnit.MICROSECONDS)) {
                this.board[currentPosition.getX()][currentPosition.getY()].unlock();
                result = true;
            } else {
                if (!checkWay(destinationPosition)) {
                    System.out.println(Thread.currentThread().getName() + " Граница карты " + destinationPosition);
                } else {
                    System.out.println(Thread.currentThread().getName() + " Ячейка заблокирована " + destinationPosition);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Check way movement unit.
     * @param destinationPosition cell destination position unit.
     * @return {@code true} if the wya is free, and {@code false} otherwise.
     */
    private boolean checkWay(Cell destinationPosition) {
        boolean result = false;
        if (destinationPosition.getX() >= 0 && destinationPosition.getX() < this.maxX && destinationPosition.getY() >= 0 && destinationPosition.getY() < this.maxY) {
            result = true;
        }
        return result;
    }

    /**
     * Lock cell.
     * @param cell for locking.
     */
    public void lockCell(Cell cell) {
        this.board[cell.getX()][cell.getY()].lock();
    }
}