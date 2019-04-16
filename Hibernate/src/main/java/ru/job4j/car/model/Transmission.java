package ru.job4j.car.model;

import java.util.Objects;

/**
 * Class Transmission.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 15.04.19.
 */
public class Transmission {
    private Long id;
    private String name;

    public Transmission() {
    }

    public Transmission(String name) {
        this.name = name;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transmission that = (Transmission) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Transmission{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
