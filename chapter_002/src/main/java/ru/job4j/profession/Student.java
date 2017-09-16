package ru.job4j.profession;

/**
 * Class Student.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 14.09.2017
 */
public class Student {
    /**
     * Name student.
     */
    private String name;

    /**
     * Constructor class Student.
     * @param name student name.
     */
    public Student(String name) {
        this.name = name;
    }

    /**
     * @return name student.
     */
    public String getName() {
        return name;
    }
}
