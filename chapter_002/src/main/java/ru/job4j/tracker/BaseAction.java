package ru.job4j.tracker;

/**
 * Class abstract BaseAction.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 28.09.2017
 */
public abstract class BaseAction implements UserAction {
    /**
     * Key menu.
     */
    private int key;
    /**
     * Name menu.
     */
    private String name;

    /**
     * Constructor class BaseAction.
     * @param key menu.
     * @param name menu.
     */
    public BaseAction(int key, String name) {
        this.key = key;
        this.name = name;
    }

    /**
     * @return int key operation.
     */
    @Override
    public int key() {
        return this.key;
    }

    /**
     * Menu info.
     * @return - String menu.
     */
    @Override
    public String info() {
        return String.format("| %s. %s", this.key, this.name);
    }
}
