package ru.job4j.sort;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Class SortUser.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 13.11.2017
 */
public class SortUser {

    /**
     * Sort User by age.
     * @param list List user.
     * @return Set user.
     */
    public Set<User> sort(List<User> list) {
        return new TreeSet<User>(list);
    }
}