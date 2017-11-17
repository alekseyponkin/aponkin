package ru.job4j.reference;

import java.util.*;

/**
 * Class SortCodesUnits.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 17.11.2017
 */
public class SortCodesUnits {
    /**
     * Sort codes units.
     * @param codes for sort.
     * @return array sorted codes.
     */
    public String[] sort(String[] codes) {
        String[] allCodes = getCodesAll(codes);
        Arrays.sort(allCodes);
        return allCodes;
    }

    /**
     * Revers sort codes units.
     * @param codes for sort.
     * @return array sorted codes.
     */
    public String[] sortReverse(String[] codes) {
        String[] allCodes = getCodesAll(codes);
        Arrays.sort(allCodes, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int len1 = o1.length();
                int len2 = o2.length();
                int lim = Math.min(len1, len2);
                char[] v1 = o1.toCharArray();
                char[] v2 = o2.toCharArray();
                int k = 0;
                while (k < lim) {
                    char c1 = v1[k];
                    char c2 = v2[k];
                    if (c1 != c2) {
                        return c2 - c1;
                    }
                    k++;
                }
                return len1 - len2;
            }
        });
        return allCodes;
    }

    /**
     * Get all codes units.
     * @param codes array codes.
     * @return array String all codes.
     */
    private String[] getCodesAll(String[] codes) {
        Set<String> setCodes = new TreeSet<>();
        for (String code : codes) {
            String[] splitCode = code.split("/");
            String tempCode = splitCode[0];
            setCodes.add(tempCode);
            for (int i = 1; i < splitCode.length; i++) {
                tempCode = tempCode.concat("/").concat(splitCode[i]);
                setCodes.add(tempCode);
            }
        }
        return setCodes.toArray(new String[]{});
    }
}