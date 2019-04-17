package ru.job4j.car.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

/**
 * Class Engine.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 15.04.19.
 */
@Entity
public class Engine {
    @Id @GeneratedValue
    private Long id;

    private String name;

    public Engine() {
    }

    public Engine(String name) {
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
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Engine engine = (Engine) o;
        return Objects.equals(name, engine.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Engine{"
                + "id=" + id
                + ", name='" + name
                + '\'' + '}';
    }
}
