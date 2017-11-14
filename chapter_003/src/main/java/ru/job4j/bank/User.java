package ru.job4j.bank;

/**
 * Class User.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 13.11.2017
 */
public class User {
    /**
     * Name of user.
     */
    String name;

    /**
     * Passport of user.
     */
    Integer passport;

    /**
     * Constructor class user.
     * @param name user name.
     * @param passport user passport.
     */
    public User(String name, Integer passport) {
        this.name = name;
        this.passport = passport;
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

        if (!name.equals(user.name)) {
            return false;
        }
        return passport.equals(user.passport);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + passport.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "User{"
                + "name='" + name
                + '\''
                + ", passport=" + passport
                + '}';
    }
}