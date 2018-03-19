package ru.job4j.nonblocking;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Class NonBlockingCacheTest.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 19.03.2018.
 */
public class NonBlockingCacheTest {

    /**
     * Test when add new user.
     */
    @Test
    public void whenAddNewUser() {
        NonBlockingCache cache = new NonBlockingCache(30);
        User userOne  = new User(1, "Ivan");
        assertTrue(cache.add(userOne));
    }

    /**
     * Test when update user.
     */
    @Test
    public void whenUpdateUser() {
        NonBlockingCache cache = new NonBlockingCache(30);
        User userOne  = new User(1, "Ivan");
        cache.add(userOne);
        userOne.setName("Alex");
        assertTrue(cache.update(userOne));
    }

    /**
     * Test when delete user.
     */
    @Test
    public void whenDeleteUser() {
        NonBlockingCache cache = new NonBlockingCache(30);
        User userOne  = new User(1, "Ivan");
        cache.add(userOne);
        assertTrue(cache.delete(userOne));
    }
}