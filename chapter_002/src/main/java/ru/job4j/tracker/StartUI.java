package ru.job4j.tracker;

/**
 * Class StartUI - start program Tracker.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 19.09.2017
 */
public class StartUI {
    /**
     * Input data.
     */
    private Input input;
    /**
     * Tracker.
     */
    private Tracker tracker;
    /**
     * Constructor class StartUI.
     * @param input enter data.
     * @param tracker tracker.
     */
    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }
    /**
     * Initialising program.
     */
    public void init() {
        MenuTracker menuTracker = new MenuTracker(this.input, this.tracker);
        menuTracker.fillActions();
        do {
            menuTracker.show();
            menuTracker.select(input.ask("Select:", menuTracker.rangeMenu()));
        } while (!"y".equals(this.input.ask("Exit? y")));
    }
    /**
     * Method main.
     * @param args args.
     */
    public static void main(String[] args) {
        new StartUI(new ValidateInput(), new Tracker()).init();
    }
}
