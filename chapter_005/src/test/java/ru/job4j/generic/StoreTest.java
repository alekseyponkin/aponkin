package ru.job4j.generic;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Class StoreTest.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 21.11.2017
 */
public class StoreTest {
    /**
     * Test use RoleStore for save Roles.
     */
    @Test
    public void whenUseRoleStore() {
        Store<Role> store = new RoleStore(5);
        store.add(new Role("1"));
        store.add(new Role("3"));
        store.add(new Role("5"));
        assertTrue(store.delete("3"));
        Role role = new Role("5");
        store.update(role);
        assertThat(((RoleStore) store).simpleArray.get(1), is(role));
    }

    /**
     * Test use UserStore for save Users.
     */
    @Test
    public void whenUseUserStore() {
        Store<User> store = new UserStore(5);
        store.add(new User("1"));
        store.add(new User("3"));
        store.add(new User("5"));
        assertTrue(store.delete("3"));
        User user = new User("5");
        store.update(user);
        assertThat(((UserStore) store).simpleArray.get(1), is(user));
    }
}