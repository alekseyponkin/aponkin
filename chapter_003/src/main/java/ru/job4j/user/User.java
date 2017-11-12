package ru.job4j.user;

/**
 * Class User.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 12.11.2017
 */
public class User {
    /**
     * Id ru.job4j.user
     */
    private Integer id;
    /**
     * Name of ru.job4j.user.
     */
    private String name;

    /**
     * City of resident ru.job4j.user.
     */
    private String city;

    /**
     * Constructor class User.
     * @param id ru.job4j.user id.
     * @param name ru.job4j.user name.
     * @param city ru.job4j.user city.
     */
    public User(Integer id, String name, String city) {
        this.id = id;
        this.name = name;
        this.city = city;
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

        if (!id.equals(user.id)) {
            return false;
        }
        if (!name.equals(user.name)) {
            return false;
        }
        return city.equals(user.city);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + city.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "User{"
                + "id=" + id
                + ", name='" + name
                + '\''
                + ", city='" + city + '\''
                + '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}