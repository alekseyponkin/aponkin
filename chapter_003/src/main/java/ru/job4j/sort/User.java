package ru.job4j.sort;

/**
 * Class User.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 13.11.2017
 */
public class User implements Comparable<User> {
    /**
     * Name of user.
     */
    String name;

    /**
     * Age of user.
     */
    Integer age;

    /**
     * Constructor class User.
     * @param name user name.
     * @param age  user age.
     */
    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public int compareTo(User o) {
        return this.age.compareTo(o.age);
    }

    @Override
    public String toString() {
        return "User{"
                + "name='" + name
                + '\''
                + ", age=" + age
                + '}';
    }
}