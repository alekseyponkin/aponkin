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
}