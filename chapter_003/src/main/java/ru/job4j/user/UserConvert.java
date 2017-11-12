package ru.job4j.user;

import java.util.HashMap;
import java.util.List;

/**
 * Class UserConvert.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 12.11.2017
 */
public class UserConvert {
    /**
     * Convert List<User> in HashMap<Integer, User>.
     * @param list  users.
     * @return HashMap<Integer, User>.
     */
    public HashMap<Integer, User> process(List<User> list) {
        HashMap<Integer, User> result = new HashMap<>();
        for (User user : list) {
           result.put(user.getId(), user);
        }
        return result;
    }
}