package ru.job4j.generic;

/**
 * Class User.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 20.11.2017
 */
public class User extends Base {
    /**
     * Id user.
     */
    String id;

    /**
     * Constructor User.
     * @param id id user.
     */
    public User(String id) {
        this.id = id;
    }

    @Override
    String getId() {
        return this.id;
    }

    @Override
    void setId(String id) {
        this.id = id;
    }
}
