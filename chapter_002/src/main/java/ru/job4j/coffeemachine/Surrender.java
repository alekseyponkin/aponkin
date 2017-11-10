package ru.job4j.coffeemachine;

import java.util.ArrayList;

/**
 * Class Surrender.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 10.11.2017
 */
public class Surrender {
    /**
     * Change money.
     * @param value money value.
     * @param price coffee cost.
     * @return
     */
    int[] changes(int value, int price) {
        int[] coins = new int[] {10, 5, 2, 1};
        int[] result;
        ArrayList<Integer> surrenderCoins = new ArrayList<>();
        int surrender = value - price;
        if (surrender == 0) {
            result = new int[]{};
        } else {
            for (int coin: coins) {
                int countCoins = surrender / coin;
                for (int i = 0; i < countCoins; i++) {
                    surrenderCoins.add(coin);
                    surrender = surrender - coin;
                }
            }
            result = new int[surrenderCoins.size()];
            for (int i = 0; i < surrenderCoins.size(); i++) {
                result[i] = surrenderCoins.get(i);
            }
        }
        return result;
    }
}
