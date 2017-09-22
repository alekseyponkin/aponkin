package ru.job4j.tracker;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Class test Menu program.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 22.09.2017
 */
public class StubInputTest {

    /**
     * Test menu Add item.
     */
    @Test
    public void whenUserAddItemThenTrackerVerifyName() {
        Tracker tracker = new Tracker();
        Input input = new StubInput(new String[]{"0", "test name", "desc", "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll()[0].getNameItem(), is("test name"));
    }

    /**
     * Test menu Update item.
     */
    @Test
    public void whenUserUpdateThenVerifyNewName() {
        Tracker tracker = new Tracker();
        tracker.add(new Item("test name", "desc"));
        Input input = new StubInput(new String[]{"2", tracker.findAll()[0].getIdItem(), "new name", "new desc", "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll()[0].getNameItem(), is("new name"));
    }

    /**
     * Test menu Delete item.
     */
    @Test
    public void whenUserDeleteThenFindItem() {
        Tracker tracker = new Tracker();
        tracker.add(new Item("test name", "desc"));
        Input input = new StubInput(new String[]{"3", tracker.findAll()[0].getIdItem(), "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll().length, is(0));
    }
}