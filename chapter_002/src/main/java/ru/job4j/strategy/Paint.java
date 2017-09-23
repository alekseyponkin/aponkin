package ru.job4j.strategy;

/**
 * Class Paint.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 23.09.2017
 */
public class Paint {
    /**
     * Method draw shape.
     * @param shape for draw.
     */
    public void draw(Shape shape) {
        System.out.println(shape.pic());
    }
}
