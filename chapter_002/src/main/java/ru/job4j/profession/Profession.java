package ru.job4j.profession;

/**
 * Profession.
 *
 * @author Ponkin Aleksey
 * @version $Id$
 * @since 14.09.2017
 */

public class Profession {

    /**
     * Name.
     */
    private String name;

    /**
     * Age.
     */
    private int age;

    /**
     * Level of education.
     */
    private String levelOfEducation;

    /**
     * Specialty.
     */
    private String specialty;

    /**
     * Constructor class Profession.
     * @param name name.
     * @param age age.
     * @param levelOfEducation level of education.
     * @param specialty specialty.
     */
    public Profession(String name, int age, String levelOfEducation, String specialty) {
        this.name = name;
        this.age = age;
        this.levelOfEducation = levelOfEducation;
        this.specialty = specialty;
    }

    /**
     * @return String name.
     */
    public String getName() {
        return name;
    }

    /**
     * @return String specialty.
     */
    public String getSpecialty() {
        return specialty;
    }

    /**
     * @return String age.
     */
    public int getAge() {
        return age;
    }

    /**
     * @return String level of education.
     */
    public String getLevelOfEducation() {
        return levelOfEducation;
    }
}
