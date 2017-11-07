package ru.job4j.chess;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Class test Chess board.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 03.11.2017
 */
public class BoardTest {

    /**
     * Test exception FigureNotFoundException.
     */
    @Test (expected = FigureNotFoundException.class)
    public void whenFigureNotFoundOnBoardException() throws Exception {
        Board board = new Board();
        board.getFigures()[0] = new Elephant(new Cell(3, 1));
        board.move(new Cell(3, 2), new Cell(3, 1));
    }

    /**
     * Test exception ImpossibleMoveException.
     */
    @Test (expected = ImpossibleMoveException.class)
    public void whenFigureImpossibleMoveException() throws Exception {
        Board board = new Board();
        board.getFigures()[0] = new Elephant(new Cell('C', 1));
        board.move(new Cell('C', 1), new Cell('C', 1));
    }

    /**
     * Test if way occupied.
     */
    @Test (expected = OccupiedWayException.class)
    public void thenOccupiedWayExceptionException() throws Exception {
        Board board = new Board();
        board.getFigures()[0] = new Elephant(new Cell(3, 1));
        board.getFigures()[1] = new Elephant(new Cell(6, 4));
        board.move(new Cell(3, 1), new Cell(8, 6));
    }

    /**
     * Test if position destination is occupied.
     */
    @Test (expected = OccupiedWayException.class)
    public void thenOccupiedDestination() throws Exception {
        Board board = new Board();
        board.getFigures()[0] = new Elephant(new Cell(3, 1));
        board.getFigures()[1] = new Elephant(new Cell(8, 6));
        board.move(new Cell(3, 1), new Cell(8, 6));
    }

    /**
     * Test clone figure.
     */
    @Test
    public void testCloneFigure() throws OccupiedWayException, ImpossibleMoveException, FigureNotFoundException {
        Board board = new Board();
        board.getFigures()[0] = new Elephant(new Cell(3, 1));
        board.move(new Cell(3, 1), new Cell(8, 6));
        assertThat(board.getFigures()[0].toString(), is("Elephant{position X=8 position Y=6}"));
    }
}