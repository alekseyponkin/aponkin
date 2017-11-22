package ru.job4j.generic;

/**
 * Class RoleStore.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 20.11.2017
 */
public class RoleStore extends AbstractStore<Role> {
    /**
     * Constructor RoleStore.
     * @param size size store for Roles.
     */
    public RoleStore(int size) {
        super(size);
    }
}