package ru.job4j.tracker.input;

import java.util.List;

/**
 * Class StabInput.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 22.09.2017
 */
public class StubInput implements Input {
    /**
     * List answers.
     */
    private List<String> answers;
    /**
     * Index position.
     */
    private int position = 0;

    /**
     * Constructor StubInput.
     * @param answers to simulate user input.
     */
    public StubInput(List<String> answers) {
        this.answers = answers;
    }

    /**
     * @param question for the user is displayed in console.
     * @return string answer.
     */
    @Override
    public String ask(String question) {
        return answers.get(position++);
    }

    /**
     * Return UnsupportedOperationException.
     * @param question question.
     * @param range range.
     * @return int.
     */
    @Override
    public int ask(String question, List<Integer> range) {
        return Integer.valueOf(answers.get(position++));
    }
}