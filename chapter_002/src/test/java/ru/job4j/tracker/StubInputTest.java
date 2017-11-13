package ru.job4j.tracker;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

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
        Input input = new StubInput(new ArrayList<>(Arrays.asList("0", "test name", "desc", "y")));
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll().get(0).getNameItem(), is("test name"));
    }

    /**
     * Test menu Update item.
     */
    @Test
    public void whenUserUpdateThenVerifyNewName() {
        Tracker tracker = new Tracker();
        tracker.add(new Item("test name", "desc"));
        Input input = new StubInput(new ArrayList<>(Arrays.asList("2", tracker.findAll().get(0).getIdItem(), "new name", "new desc", "y")));
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll().get(0).getNameItem(), is("new name"));
    }

    /**
     * Test menu Delete item.
     */
    @Test
    public void whenUserDeleteThenFindItem() {
        Tracker tracker = new Tracker();
        tracker.add(new Item("test name", "desc"));
        Input input = new StubInput(new ArrayList<>(Arrays.asList("3", tracker.findAll().get(0).getIdItem(), "y")));
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll().size(), is(0));
    }
}