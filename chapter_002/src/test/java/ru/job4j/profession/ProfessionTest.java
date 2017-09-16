package ru.job4j.profession;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Class Profession test.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 16.09.2017
 */
public class ProfessionTest {
    /**
     * Test method teach class Teacher.
     */
    @Test
    public void thenTeacherTeachingStudent() {
        Teacher teacher = new Teacher("Иван", 25, "высшее", "географии");
        String result = teacher.teach(new Student("Коля"));
        String expect = "Учитель географии Иван учит студента Коля";
        assertThat(result, is(expect));
    }

    /**
     * Test method create class Engineer.
     */
    @Test
    public void thenEngineerCreatingDevice() {
        Engineer engineer = new Engineer("Алексей", 31, "высшее", "электроники");
        String result = engineer.create(new Device("телефон"));
        String expect = "Инженер электроники Алексей разрабатывает устройство телефон";
        assertThat(result, is(expect));
    }

    /**
     * Test method cure class Doctor.
     */
    @Test
    public void tnetDoctorCurePatient() {
        Doctor doctor = new Doctor("Павел", 50, "высшее", "терапевт");
        String result = doctor.cure(new Patient("Виктор"));
        String expect = "Доктор терапевт Павел лечит пациента Виктор";
        assertThat(result, is(expect));
    }
}