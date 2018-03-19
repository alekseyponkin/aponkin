package ru.job4j.nonblocking;

import java.util.Objects;

/**
 * Class User.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 18.03.2018.
 */
public class User {
    /**
     * id user.
     */
    private final int id;

    /**
     * User name.
     */
    private String name;

    private int version = 0;

    /**
     * Constructor  User.
     * @param id User.
     * @param name User.
     */
    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}