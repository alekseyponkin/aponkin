package ru.job4j.tracker;

/**
 * Class //todo.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 22.09.2017
 */
public class StubInput implements Input {
    /**
     *
     */
    private String[] answers;
    /**
     *
     */
    private int position = 0;

    /**
     * Constructor StubInput.
     * @param answers to simulate user input.
     */
    public StubInput(String[] answers) {
        this.answers = answers;
    }

    /**
     * @param question for the user is displayed in console.
     * @return
     */
    @Override
    public String ask(String question) {
        return answers[position++];
    }
}
