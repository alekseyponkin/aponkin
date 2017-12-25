package ru.job4j.synchronizy;

/**
 * Class User.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 25.12.2017.
 */
public class User {
    /**
     * Id user.
     */
    private int id;
    /**
     * Amount money user.
     */
    private  int amount;

    /**
     * Constructor class User.
     * @param id id user.
     * @param amount amount money user.
     */
    public User(int id, int amount) {
        this.id = id;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}