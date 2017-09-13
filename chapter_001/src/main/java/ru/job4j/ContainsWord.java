package ru.job4j;

/**
 * ContainsWord.
 *
 * @author Ponkin Aleksey
 * @version $Id$
 * @since 10.06.2017
 */

public class ContainsWord {
    /**
     * contains - метод который проверяет содержит ли оригинальное слово искомое слово.
     * @param origin - слово в которй ищем.
     * @param sub - слово которое ищем.
     * @return true - если содержит.
     */
    public boolean contains(String origin, String sub) {
        char[] wordOrigin = origin.toCharArray();
        char[] wordSub = sub.toCharArray();
        boolean result = false;
        for (int i = 0; i <  wordOrigin.length; i++) {
            int j = 0;
            while (wordSub[j] ==  wordOrigin[i]) {
                j++;
                i++;
                if (j == wordSub.length || i ==  wordOrigin.length) {
                    break;
                }
            }
            if (j == wordSub.length) {
                result = true;
                break;
            }
        }
        return result;
    }
}
