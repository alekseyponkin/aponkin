package ru.job4j.sort;

import java.util.*;

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

    /**
     * Sort User by length name.
     * @param list source.
     * @return sorted List user.
     */
    public List<User> sortNameLength(List<User> list) {
        list.sort(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                int o1Lengt = o1.name.length();
                int o2Lengt = o2.name.length();
                int result = 1;
                if (o1Lengt == o2Lengt) {
                    result = 0;
                } else if (o1Lengt < o2Lengt) {
                    result = -1;
                }
                return result;
            }
        });
        return new ArrayList<>(list);
    }

    /**
     * Sort User by name end age.
     * @param list source.
     * @return sorted List user.
     */
    public List<User> sortByAllFields(List<User> list) {
        list.sort(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                int result = o1.name.compareTo(o2.name);
                 if (result == 0) {
                     result = o1.compareTo(o2);
                 }
                 return result;
            }
        });
        return new ArrayList<>(list);
    }
}