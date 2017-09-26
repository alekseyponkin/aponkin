package ru.job4j.tracker;

/**
 * Class UseAction.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 26.09.2017
 */
public interface UserAction {
    /**
     * @return key menu.
     */
    int key();

    /**
     * @param input - input data.
     * @param tracker - tacker.
     */
    void execut(Input input, Tracker tracker);

    /**
     * @return string menu.
     */
    String info();
}
