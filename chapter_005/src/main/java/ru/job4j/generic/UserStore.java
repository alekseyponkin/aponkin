package ru.job4j.generic;

/**
 * Class UserStore.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 20.11.2017
 */
public class UserStore extends AbstractStore<User> {
/**
     * Constructor UserStore.
     * @param size size store for Users.
     */
    public UserStore(int size) {
        super(size);
    }
}
