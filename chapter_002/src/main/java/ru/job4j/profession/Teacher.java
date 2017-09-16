package ru.job4j.profession;
/**
*
*/
public class Teacher extends Profession {
    /**
     * Constructor class Teacher.
     * @param name Teacher name.
     * @param age Teacher age.
     * @param levelOfEducation  Teacher level of education.
     * @param specialty Teacher specialty.
     */
    public Teacher(String name, int age, String levelOfEducation, String specialty) {
        super(name, age, levelOfEducation, specialty);
    }

    /**
     * Method learning student.
     * @param student learning student.
     * @return String description teaching student.
     */
    public String teach(Student student) {
        return String.format("Учитель %s %s учит студента %s", this.getSpecialty(), this.getName(), student.getName());
    }
}
