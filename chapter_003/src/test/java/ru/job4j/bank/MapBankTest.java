package ru.job4j.bank;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Class MapBank test.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 14.11.2017
 */
public class MapBankTest {
    /**
     * Test adding new user in MapBank.
     */
    @Test
    public void whenAddNewUser() {
        MapBank mapBank = new MapBank();
        User user = new User("Alex", 33444232);
        mapBank.addUser(user);
        assertTrue(mapBank.map.containsKey(user));
    }

    /**
     * Test deleting user in MapBank.
     */
    @Test
    public void whenDeleteUser() {
        MapBank mapBank = new MapBank();
        User user = new User("Alex", 33444232);
        mapBank.addUser(user);
        mapBank.deleteUser(user);
        assertFalse(mapBank.map.containsKey(user));
    }

    /**
     * Test adding new account for user in MapBank.
     */
    @Test
    public void whenAddNewAccountToUser() {
        MapBank mapBank = new MapBank();
        User user = new User("Alex", 33444232);
        mapBank.addUser(user);
        Account account = new Account(111, 10_000D);
        mapBank.addAccountToUser(user, account);
        assertTrue(mapBank.map.get(user).contains(account));
    }

    /**
     * Test deleting account for user in MapBank.
     */
    @Test
    public void whenDeleteAccountFromUser() {
        MapBank mapBank = new MapBank();
        User user = new User("Alex", 33444232);
        mapBank.addUser(user);
        Account account = new Account(111, 10_000D);
        mapBank.addAccountToUser(user, account);
        mapBank.deleteAccountFromUser(user, account);
        assertFalse(mapBank.map.get(user).contains(account));
    }

    /**
     * Test getting lise user from MapBank.
     */
    @Test
    public void getUserAccount() {
        MapBank mapBank = new MapBank();
        User user = new User("Alex", 33444232);
        mapBank.addUser(user);
        Account account = new Account(111, 10_000D);
        Account account2 = new Account(222, 100_000D);
        mapBank.addAccountToUser(user, account);
        mapBank.addAccountToUser(user, account2);
        assertThat(mapBank.map.get(user), is(new ArrayList<Account>(Arrays.asList(account, account2))));
    }

    /**
     * Test transfer money from account users.
     */
    @Test
    public void whenTransferMoneySuccessful() {
        MapBank mapBank = new MapBank();
        User user = new User("Alex", 33444232);
        User user2 = new User("Lex", 33444222);
        mapBank.addUser(user);
        mapBank.addUser(user2);
        Account account = new Account(111, 100_000D);
        Account account2 = new Account(222, 10_000D);
        mapBank.addAccountToUser(user, account);
        mapBank.addAccountToUser(user2, account2);
        assertTrue(mapBank.transferMoney(user, account, user2, account2, 10_000));
    }

    /**
     * Test transfer money if little money.
     */
    @Test
    public void transferMoneyLittleMoney() {
        MapBank mapBank = new MapBank();
        User user = new User("Alex", 33444232);
        User user2 = new User("Lex", 33444222);
        mapBank.addUser(user);
        mapBank.addUser(user2);
        Account account = new Account(111, 100_000D);
        Account account2 = new Account(222, 10_000D);
        mapBank.addAccountToUser(user, account);
        mapBank.addAccountToUser(user2, account2);
        assertFalse(mapBank.transferMoney(user, account, user2, account2, 200_000));
    }

    /**
     * Test transfer money one user between account.
     */
    @Test
    public void transferMoneyUserBetweenAccount() {
        MapBank mapBank = new MapBank();
        User user = new User("Alex", 33444232);
        mapBank.addUser(user);
        Account account = new Account(111, 100_000D);
        Account account2 = new Account(222, 10_000D);
        mapBank.addAccountToUser(user, account);
        mapBank.addAccountToUser(user, account2);
        assertTrue(mapBank.transferMoney(user, account, user, account2, 50_000));
    }
}