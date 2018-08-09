package ru.job4j.model;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Class User.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 18.05.2018.
 */
public class User {
    /**
     * Id user.
     */
    private Long id = 0L;
    /**
     * Name user.
     */
    private String name;
    /**
     * Login user.
     */
    private String login;

    /**
     * Password user.
     */
    private String password;

    /**
     * Email user.
     */
    private String email;
    /**
     * Date create user.
     */
    private LocalDateTime createDate;
    /**
     * Role user.
     */
    private Role role;
    /**
     * Default constructor.
     */
    public User() {
    }

    /**
     * Constructor User with name, login, email.
     * @param name user.
     * @param login user.
     * @param email user.
     */
    public User(String name, String login, String email) {
        this.name = name;
        this.login = login;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{"
                + "id='" + id + '\''
                + "name='" + name + '\''
                + ", login='" + login + '\''
                + ", email='" + email + '\''
                + ", createDate=" + createDate
                + '}';
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
        return Objects.equals(id, user.id)
                && Objects.equals(name, user.name)
                && Objects.equals(login, user.login)
                && Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, login, email);
    }
}