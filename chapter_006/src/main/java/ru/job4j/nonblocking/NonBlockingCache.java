package ru.job4j.nonblocking;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Class NonBlockingCache.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 18.03.2018.
 */
public class NonBlockingCache {

    /**
     * ConcurrentHashMap for storage Users.
     */
    private ConcurrentHashMap<Integer, User> cache;

    /**
     * Constructor NonBlockingCache with initial max size.
     * @param size max size ConcurrentHashMap.
     */
    public NonBlockingCache(int size) {
        this.cache = new ConcurrentHashMap<>(size);
    }

    /**
     * Add new User.
     * @param user for adding.
     * @return true if adding successful, false otherwise.
     */
    public boolean add(User user) {
        Boolean result = false;
        if (this.cache.putIfAbsent(user.getId(), user) == null) {
            result = true;
        }
        return result;
    }

    /**
     * Update User.
     * @param user for updating.
     * @return true if updating successful, false otherwise.
     * @throws OptimisticException if updating fail.
     */
    public boolean update(User user) throws OptimisticException {
        Boolean result = false;
        if (this.cache.computeIfPresent(user.getId(), (k, v) -> {
            user.setVersion(user.getVersion() + 1);
            if (v.getVersion() != user.getVersion()) {
                throw new OptimisticException();
            }
            return  user;
        }) != null) {
            result = true;
        }
        return result;
    }

    /**
     * Delete User.
     * @param user for delete.
     * @return true if deleting successful, false otherwise.
     */
    public boolean delete(User user) {
        Boolean result = false;
        if (cache.remove(user.getId()) != null) {
            result = true;
        }
        return result;
    }
}