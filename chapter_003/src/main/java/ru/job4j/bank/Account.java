package ru.job4j.bank;

/**
 * Class Account.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 13.11.2017
 */
public class Account {
    /**
     * Requisites of account.
     */
    Integer requisites;

    /**
     * Value money of account.
     */
    Double value;

    /**
     * Constructor class Account.
     * @param value money of account.
     * @param requisites of account.
     */
    public Account(Integer requisites, Double value) {
        this.value = value;
        this.requisites = requisites;
    }

    @Override
    public String toString() {
        return "Account{"
                + "requisites=" + requisites
                + ", value=" + value
                + '}';
    }
}