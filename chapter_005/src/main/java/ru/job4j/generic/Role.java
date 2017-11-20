package ru.job4j.generic;

/**
 * Class Role.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 20.11.2017
 */
public class Role extends Base {
    /**
     * Id Role.
     */
    String id;

    /**
     * Constructor Role.
     * @param id id role.
     */
    public Role(String id) {
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