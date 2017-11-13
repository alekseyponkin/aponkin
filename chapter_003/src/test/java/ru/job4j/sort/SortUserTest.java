package ru.job4j.sort;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Class SortUserTest.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 13.11.2017
 */
public class SortUserTest {
    /**
     * Test sort User by age.
     */
    @Test
    public void whenSortUserByAge() {
        SortUser sortUser = new SortUser();
        Set<User> result = sortUser.sort(new ArrayList<>(Arrays.asList(new User("Ivan", 25), new User("Vasya", 13), new User("Alex", 33))));
        String expect = "[User{name='Vasya', age=13}, User{name='Ivan', age=25}, User{name='Alex', age=33}]";
        assertThat(result.toString(), is(expect));
    }

    /**
     * Test sort User by name.
     */
    @Test
    public void whenSortUserByName() {
        SortUser sortUser = new SortUser();
        List<User> result = sortUser.sortNameLength(new ArrayList<>(Arrays.asList(new User("Ina", 25), new User("Vasya", 13), new User("Alex", 33))));
        String expect = "[User{name='Ina', age=25}, User{name='Alex', age=33}, User{name='Vasya', age=13}]";
        assertThat(result.toString(), is(expect));
    }

    /**
     * Test sort User by name end age.
     */
    @Test
    public void whenSortUserByAllFields() {
        SortUser sortUser = new SortUser();
        List<User> result = sortUser.sortByAllFields(new ArrayList<>(Arrays.asList(new User("Alex", 50), new User("Ivan", 25), new User("Ivan", 13), new User("Alex", 33))));
        String expect = "[User{name='Alex', age=33}, User{name='Alex', age=50}, User{name='Ivan', age=13}, User{name='Ivan', age=25}]";
        assertThat(result.toString(), is(expect));
    }
}