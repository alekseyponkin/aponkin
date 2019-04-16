package ru.job4j.car.model;

import java.util.Objects;

/**
 * Class Car.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 15.04.19.
 */
public class Car {
    private Long id;
    private String name;
    private Engine engine;
    private Transmission transmission;
    private Body body;


    public Car() {
    }

    public Car(String name, Engine engine, Transmission transmission, Body body) {
        this.name = name;
        this.engine = engine;
        this.transmission = transmission;
        this.body = body;
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

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(name, car.name) &&
                Objects.equals(engine, car.engine) &&
                Objects.equals(transmission, car.transmission) &&
                Objects.equals(body, car.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, engine, transmission, body);
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", engine=" + engine +
                ", transmission=" + transmission +
                ", body=" + body +
                '}';
    }
}