package ru.job4j.validate;

import ru.job4j.dao.MemoryStore;
import ru.job4j.dao.Store;
import ru.job4j.model.User;

import java.util.List;

/**
 * Class ValidateMemoryService.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 21.05.2018.
 */
public class ValidateMemoryService {
    /**
     * Single instance ValidateMemoryService.
     */
    private final static ValidateMemoryService OUR_INSTANCE = new ValidateMemoryService();
    /**
     * Store users.
     */
    private final Store<User, Long> store = MemoryStore.getInstance();

    /**
     * Closed constructor.
     */
    private ValidateMemoryService() {
    }

    /**
     * Get instance ValidateMemoryService.
     * @return single instance.
     */
    public static ValidateMemoryService getInstance() {
        return OUR_INSTANCE;
    }

    /**
     * Validate and add user in store.
     * @param user
     * @return true if successful otherwise false.
     */
    public boolean add(User user) {
        boolean result = true;
        for (User userCheckLoginAndEmail: this.findAll()) {
            if (userCheckLoginAndEmail.getLogin().equals(user.getLogin())
                    || userCheckLoginAndEmail.getEmail().equals(user.getEmail())) {
                result = false;
                break;
            }
        }
        if (result) {
            this.store.add(user);
        }
        return result;
    }

    /**
     * Check that store contains user and update him in store.
     * @param user
     * @return true if successful otherwise false.
     */
    public boolean update(User user) {
        boolean result = false;
        if (this.findById(user.getId()) != null) {
            result = this.store.update(user);
        }
        return result;
    }

    /**
     * Check that the store contains user and delete him from store.
     * @param user
     * @return true if successful otherwise false.
     */
    public boolean delete(User user) {
        boolean result = false;
        if (this.findById(user.getId()) != null) {
            result = this.store.delete(user);
        }
        return result;
    }

    /**
     * Find all users.
     * @return list users.
     */
    public List<User> findAll() {
        return this.store.findAll();
    }

    /**
     * Find user by id.
     * @param id user.
     * @return true if successful otherwise false.
     */
    public User findById(Long id) {
        return this.store.findById(id);
    }
}