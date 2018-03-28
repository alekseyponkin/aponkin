package ru.job4j.bomberman;

/**
 * Class Game.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 26.03.2018.
 */
public class Game {

    /**
     * Initialization game.
     */
    public void gameInit() {
        GameBoard gameBoard = new GameBoard(10, 10);
        Thread boombermen = new Thread(new Bomberman(gameBoard, new Cell(1, 1)));
        Thread boombermen1 = new Thread(new Bomberman(gameBoard, new Cell(1, 1)));
        Thread boombermen2 = new Thread(new Bomberman(gameBoard, new Cell(1, 1)));
        Thread boombermen3 = new Thread(new Bomberman(gameBoard, new Cell(1, 1)));
        boombermen.setName("Bombermen-1");
        boombermen1.setName("Bombermen-2");
        boombermen2.setName("Bombermen-3");
        boombermen3.setName("Bombermen-4");
        boombermen.start();
        boombermen1.start();
        boombermen2.start();
        boombermen3.start();
        try {
            boombermen.join();
            boombermen1.join();
            boombermen2.join();
            boombermen3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Game().gameInit();
    }
}