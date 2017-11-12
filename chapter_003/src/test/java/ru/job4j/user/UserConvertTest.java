package ru.job4j.user;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Class test ConvertUser.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 13.11.2017
 */
public class UserConvertTest {
    /**
     * Test method convert List Users iv HashMap Users.
     */
    @Test
    public void whenConvertListUserInHashMap() {
        UserConvert userConvert = new UserConvert();
        HashMap<Integer, User> result = userConvert.process(Arrays.asList(new User(5, "Alex", "Moscow"), new User(3, "Nikola", "Yaroslavl")));
        HashMap<Integer, User> expect = new HashMap<>();
        expect.put(3, new User(3, "Nikola", "Yaroslavl"));
        expect.put(5, new User(5, "Alex", "Moscow"));
        assertThat(result, is(expect));
    }
}