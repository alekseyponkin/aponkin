package ru.job4j.tracker;

import org.junit.Test;
import ru.job4j.tracker.entity.Comment;
import ru.job4j.tracker.entity.Item;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Class test TrackerSQLTest.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 18.01.2019
*/
public class TrackerSQLTest {

    private final Item itemFirst = new Item("testFirst", "testDescriptionFirst");
    private final Item itemSecond = new Item("testSecond", "testDescripitonSecond");

    /**
     * Init connection database.
     * @return connect in database.
     */
    public Connection init() {
        try (InputStream in = TrackerSQL.class.getClassLoader().getResourceAsStream("tracker/trackerSQL.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("jdbc.class"));
            return DriverManager.getConnection(
                    config.getProperty("jdbc.url"),
                    config.getProperty("jdbc.name"),
                    config.getProperty("jdbc.password")

            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * Test add new item.
     */
    @Test
    public void whenAddNewItemThenReturnNameAddedItem() throws Exception {
        try (TrackerSQL tracker = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            int idItem = tracker.add(this.itemFirst);
            assertEquals(this.itemFirst.getNameItem(), tracker.findById(String.valueOf(idItem)).getNameItem());
        }
    }

    /**
     * Test update parameters item.
     */
    @Test
    public void whenUpdateNameItemThenReturnNewName() throws Exception {
        try (TrackerSQL tracker = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            int idItem = tracker.add(this.itemFirst);
            this.itemSecond.setIdItem(idItem);
            tracker.update(this.itemSecond);
            assertThat(tracker.findById(String.valueOf(idItem)).getNameItem(), is("testSecond"));
        }
    }

    /**
     * Test delete item.
     */
    @Test
    public void whenDeleteItemThenSearchIdItem() throws Exception {
        try (TrackerSQL tracker = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            int idItem = tracker.add(this.itemFirst);
            this.itemFirst.setIdItem(idItem);
            tracker.delete(this.itemFirst);
            assertNull(tracker.findById(String.valueOf(idItem)));
        }
    }

    /**
     * Test find all item.
     */
    @Test
    public void whenFindAllItemThenVerifyLengthArray() throws Exception {
        try (TrackerSQL tracker = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            tracker.add(this.itemFirst);
            tracker.add(this.itemSecond);
            assertThat(tracker.findAll().size(), is(2));
        }
    }

    /**
     * Test find item with the specified name.
     */
    @Test
    public void whenFindByNameDbThenVerifyNameReturnArray() throws Exception {
        try (TrackerSQL tracker = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            tracker.add(this.itemFirst);
            tracker.add(this.itemSecond);
            assertThat(tracker.findByName("testSecond").get(0).getNameItem(), is("testSecond"));
        }
    }

    /**
     * Test find item with the id.
     */
    @Test
    public void whenFindByIdThenVerifyReturnName() throws Exception {
        try (TrackerSQL tracker = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            tracker.add(this.itemFirst);
            int idItemSecond = tracker.add(this.itemSecond);
            String expect = this.itemSecond.getNameItem();
            assertEquals(tracker.findById(String.valueOf(idItemSecond)).getNameItem(), expect);
        }
    }

    /**
     * Test adding new comment.
     */
    @Test
    public void whenAddNewCommentThenVerifyTextComment() throws Exception {
        try (TrackerSQL tracker = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            int idItem = tracker.add(itemFirst);
            Comment comment = new Comment("First comment");
            tracker.addComment(comment, String.valueOf(idItem));
            String expect = comment.getTextComment();
            assertEquals(tracker.findAllComments(String.valueOf(idItem)).get(0).getTextComment(), expect);
        }
    }
}