package ru.job4j.tracker;

import org.junit.Test;
import ru.job4j.tracker.entity.Comment;
import ru.job4j.tracker.entity.Item;
import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.input.StubInput;

import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Class test Menu program.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 27.04.2018
 */
public class StubInputTest {

    /**
     * Test menu Add item.
     */
    @Test
    public void whenUserAddItemThenTrackerVerifyName() throws Exception {
        try (Tracker tracker = new Tracker()) {
            Input input = new StubInput(new ArrayList<>(Arrays.asList("0", "test name", "desc", "y")));
            new StartUI(input, tracker).init();
            assertThat(tracker.findAll().get(0).getNameItem(), is("test name"));
        }
    }

    /**
     * Test menu Update item.
     */
    @Test
    public void whenUserUpdateThenVerifyNewName() throws Exception {
        try (Tracker tracker = new Tracker()) {
            tracker.add(new Item("test name", "desc"));
            Input input = new StubInput(new ArrayList<>(Arrays.asList("4", tracker.findAll().get(0).getIdItem().toString(), "new name", "new desc", "y")));
            new StartUI(input, tracker).init();
            assertThat(tracker.findAll().get(0).getNameItem(), is("new name"));
        }
    }

    /**
     * Test menu Show all item.
     */
    @Test
    public void whenUserAddTwoItemThemShowAllItem() throws Exception {
        try (Tracker tracker = new Tracker()) {
            tracker.add(new Item("test name", "desc"));
            tracker.add(new Item("test name two", "desc two"));
            Input input = new StubInput(new ArrayList<>(Arrays.asList("2", "y")));
            new StartUI(input, tracker).init();
            assertThat(tracker.findAll().size(), is(2));
        }
    }

    /**
     * Test menu Show all comments.
     */
    @Test
    public void whenAddCommentThenShowAllComments() throws Exception {
        try (Tracker tracker = new Tracker()) {
            int idItem = tracker.add(new Item("test name", "desc"));
            tracker.addComment(new Comment("First comment"), String.valueOf(idItem));
            Input input = new StubInput(new ArrayList<>(Arrays.asList("3", tracker.findAll().get(0).getIdItem().toString(), "y")));
            new StartUI(input, tracker).init();
            assertThat(tracker.findAllComments(tracker.findAll().get(0).getIdItem().toString()).size(), is(1));
        }
    }
}