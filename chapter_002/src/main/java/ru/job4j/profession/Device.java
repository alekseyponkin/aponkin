package ru.job4j.profession;

/**
 * Class Device.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 15.09.2017
 */
public class Device {
    /**
     * Mane device.
     */
    private String name;

    /**
     * Constructor class Device.
     * @param name device name.
     */
    public Device(String name) {
        this.name = name;
    }

    /**
     * @return String name device.
     */
    public String getName() {
        return name;
    }
}
