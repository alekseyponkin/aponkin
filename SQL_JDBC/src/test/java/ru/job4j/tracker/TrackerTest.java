package ru.job4j.tracker;

import org.junit.Test;
import ru.job4j.tracker.entity.Comment;
import ru.job4j.tracker.entity.Item;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

/**
 * Class test Tacker.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 27.04.2018
 */
public class TrackerTest {
    /**
     * Test add new item.
     */
    @Test
    public void whenAddNewItemThenReturnNameAddedItem() throws Exception {
        try (Tracker tracker = new Tracker()) {
        Item item = new Item("testName", "testDescription");
        int idItem = tracker.add(item);
        assertEquals(tracker.findById(String.valueOf(idItem)).getNameItem(), item.getNameItem());
        }
    }

    /**
     * Test update parameters item.
     */
    @Test
    public void whenUpdateNameItemThenReturnNewName() throws Exception {
        try (Tracker tracker = new Tracker()) {
            Item itemFirst = new Item("testFirst", "testDescriptionFirst");
            int idItem = tracker.add(itemFirst);
            Item itemSecond = new Item("testSecond", "testDescripitonSecond");
            itemSecond.setIdItem(idItem);
            tracker.update(itemSecond);
            assertThat(tracker.findById(String.valueOf(idItem)).getNameItem(), is("testSecond"));
        }
    }

    /**
     * Test delete item.
     */
    @Test
    public void whenDeleteItemThenSearchIdItem() throws Exception {
        try (Tracker tracker = new Tracker()) {
            Item itemFirst = new Item("testFirst", "testDescriptionFirst");
            int idItem = tracker.add(itemFirst);
            itemFirst.setIdItem(idItem);
            tracker.delete(itemFirst);
            assertNull(tracker.findById(String.valueOf(idItem)));
        }
    }

    /**
     * Test find all item.
     */
    @Test
    public void whenFindAllItemThenVerifyLengthArray() throws Exception {
        try (Tracker tracker = new Tracker()) {
            Item itemFirst = new Item("testFirst", "testDescriptionFirst");
            tracker.add(itemFirst);
            Item itemSecond = new Item("testSecond", "testDescripitonSecond");
            tracker.add(itemSecond);
            assertThat(tracker.findAll().size(), is(2));
        }
    }

    /**
     * Test find item with the specified name.
     */
    @Test
    public void whenFindByNameDbThenVerifyNameReturnArray() throws Exception {
        try (Tracker tracker = new Tracker()) {
            Item itemFirst = new Item("testFirst", "testDescriptionFirst");
            tracker.add(itemFirst);
            Item itemSecond = new Item("testSecond", "testDescripitonSecond");
            tracker.add(itemSecond);
            assertThat(tracker.findByName("testSecond").get(0).getNameItem(), is("testSecond"));
        }
    }

    /**
     * Test find item with the id.
     */
    @Test
    public void whenFindByIdThenVerifyReturnName() throws Exception {
        try (Tracker tracker = new Tracker()) {
            Item itemFirst = new Item("testFirst", "testDescriptionFirst");
            tracker.add(itemFirst);
            Item itemSecond = new Item("testSecond", "testDescripitonSecond");
            int idItemSecond = tracker.add(itemSecond);
            String expect = itemSecond.getNameItem();
            assertEquals(tracker.findById(String.valueOf(idItemSecond)).getNameItem(), expect);
        }
    }

    /**
     * Test adding new comment.
     */
    @Test
    public void whenAddNewCommentThenVerifyTextComment() throws Exception {
        try (Tracker tracker = new Tracker()) {
            Item itemFirst = new Item("testFirst", "testDescriptionFirst");
            int idItem = tracker.add(itemFirst);
            Comment comment = new Comment("First comment");
            tracker.addComment(comment, String.valueOf(idItem));
            String expect = comment.getTextComment();
            assertEquals(tracker.findAllComments(String.valueOf(idItem)).get(0).getTextComment(), expect);
        }
    }
}