package ru.job4j.tracker;

import java.util.ArrayList;

/**
 * Class ValidateInput.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 27.09.2017
 */
public class ValidateInput extends ConsoleInput {
    /**
     * Override method checking input data.
     * @param question
     * @param range
     * @return
     */
    @Override
    public int ask(String question, ArrayList<Integer> range) {
        boolean invalid = true;
        int value = -1;
        do {
            try {
                value = super.ask(question, range);
                invalid = false;
            } catch (MenuOutException e) {
                System.out.println("Please select key from menu.");
            } catch (NumberFormatException e) {
                System.out.println("Please enter validate data again.");
            }
        } while (invalid);
        return value;
    }
}
