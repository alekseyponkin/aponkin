package ru.job4j.synchronizy;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Class UserStorageTest.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 25.12.2017.
 */
public class UserStorageTest {
    private UserStorage storage;
    private User user1;
    private User user2;

    @Before
    public void setUp() {
        storage = new UserStorage();
        user1 = new User(1, 100);
        user2 = new User(2, 200);
    }

    /**
     * Test adding users.
     */
    @Test
    public void testAddingUsers() {
        assertTrue(storage.add(user1));
        storage.add(user2);
        assertFalse(storage.add(new User(2, 500)));
    }

    /**
     * Test updating users.
     */
    @Test
    public void testUpdatingUsers() {
        storage.add(user2);
        assertTrue(storage.update(new User(2, 300)));
        assertFalse(storage.update(new User(3, 300)));
    }

    /**
     * Test deleting users.
     */
    @Test
    public void testDeletingUsers() {
        storage.add(user1);
        assertTrue(storage.delete(user1));
        assertFalse(storage.update(user2));
    }

    /**
     * Test when transfer money between users successful.
     */
    @Test
    public void testTransferMoneyBetweenUsers() {
        storage.add(user1);
        storage.add(user2);
        assertTrue(storage.transfer(1, 2, 50));
        assertFalse(storage.transfer(1, 2, 200));
    }
}