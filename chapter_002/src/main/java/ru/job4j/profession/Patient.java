package ru.job4j.profession;

/**
 * Class Patient.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 15.09.2017
 */
public class Patient {
    /**
     * Name device.
     */
    private String name;

    /**
     * Constructor class Device.
     * @param name device name.
     */
    public Patient(String name) {
        this.name = name;
    }

    /**
     * @return String device name.
     */
    public String getName() {
        return name;
    }
}
