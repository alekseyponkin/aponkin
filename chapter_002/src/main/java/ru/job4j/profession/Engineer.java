package ru.job4j.profession;

/**
 * Class Engineer.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 15.09.2017
 */
public class Engineer extends Profession {

    /**
     * Constructor class Engineer.
     * @param name engineer name.
     * @param age engineer age.
     * @param levelOfEducation engineer level of education.
     * @param specialty engineer specialty.
     */
    public Engineer(String name, int age, String levelOfEducation, String specialty) {
        super(name, age, levelOfEducation, specialty);
    }

    /**
     * Method creating device.
     * @param device creating engineer device.
     * @return String description creating device.
     */
    public String create(Device device) {
        return String.format("Инженер %s %s разрабатывает устройство %s", this.getSpecialty(), this.getName(), device.getName());
    }
}
