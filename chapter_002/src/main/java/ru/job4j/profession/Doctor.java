package ru.job4j.profession;

/**
 * Class Doctor.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 15.09.2017
 */
public class Doctor extends Profession {
    /**
     * Constructor class Doctor.
     * @param name doctor name.
     * @param age doctor age.
     * @param levelOfEducation doctor level of education.
     * @param specialty doctor specialty.
     */
    public Doctor(String name, int age, String levelOfEducation, String specialty) {
        super(name, age, levelOfEducation, specialty);
    }

    /**
     * Method cure sick patient.
     * @param patient sick patient.
     * @return String description curing patient.
     */
    public String cure(Patient patient) {
        return String .format("Доктор %s %s лечит пациента %s", this.getSpecialty(), this.getName(), patient.getName());
    }
}
