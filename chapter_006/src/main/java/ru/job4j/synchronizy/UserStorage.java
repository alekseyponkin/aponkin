package ru.job4j.synchronizy;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.Map;

/**
 * Class UserStorage.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 25.12.2017.
 */
@ThreadSafe
public class UserStorage {
    /**
     * Storage for Users.
     */
    @GuardedBy("this")
    Map<Integer, User> usersStorage;

    /**
     * Constructor class UserStorage.
     */
    public UserStorage() {
        this.usersStorage = new HashMap<>();
    }

    /**
     * Adding user.
     * @param user user for adding.
     * @return true if successful adding.
     */
    public boolean add(User user) {
        boolean result = false;
        synchronized (this) {
            if (this.usersStorage.putIfAbsent(user.getId(), user) == null) {
                result = true;
            }
        }
        return result;
    }

    /**
     * Updating user.
     * @param user user for updating.
     * @return true if successful updating.
     */
    public boolean update(User user) {
        boolean result = false;
        synchronized (this) {
            if (this.usersStorage.computeIfPresent(user.getId(), (k, v) -> v = user) != null) {
                result = true;
            }
        }
        return result;
    }

    /**
     * Deleting user.
     * @param user user for deleting.
     * @return true if successful deleting.
     */
    public boolean delete(User user) {
        boolean result = false;
        synchronized (this) {
            if (this.usersStorage.remove(user.getId()) != null) {
                result = true;
            }
        }
        return result;
    }

    /**
     * Transfer money between users.
     * @param fromId id user from transfer.
     * @param toId id user to transfer.
     * @param amout count money.
     * @return true if transfer successful.
     */
    public boolean transfer(int fromId, int toId, int amout) {
        boolean result = false;
        synchronized (this) {
            User userTo = this.usersStorage.get(toId);
            User userFrom = this.usersStorage.get(fromId);
            if (userFrom != null && userTo != null && userFrom.getAmount() >= amout) {
                userFrom.setAmount(userFrom.getAmount() - amout);
                userTo.setAmount(userTo.getAmount() + amout);
                result = true;
            }
        }
        return result;
    }
}