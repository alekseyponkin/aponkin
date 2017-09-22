package ru.job4j.tracker;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

/**
 * Class test Tacker.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 17.09.2017
 */
public class TrackerTest {
    /**
     * Test add new item.
     */
    @Test
    public void whenAddNewItemThenReturnNameAddedItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("testName", "testDescription");
        tracker.add(item);
        assertThat(tracker.findAll()[0], is(item));
    }

    /**
     * Test update parameters item.
     */
    @Test
    public void whenUpdateNameItemThenReturnNewName() {
        Tracker tracker = new Tracker();
        Item itemFirst = new Item("testFirst", "testDescriptionFirst");
        tracker.add(itemFirst);
        Item itemSecond = new Item("testSecond", "testDescripitonSecond");
        itemSecond.setIdItem(itemFirst.getIdItem());
        tracker.update(itemSecond);
        assertThat(tracker.findById(itemFirst.getIdItem()).getNameItem(), is("testSecond"));
    }

    /**
     * Test delete item.
     */
    @Test
    public void whenDeleteItemThenSearchIdItem() {
        Tracker tracker = new Tracker();
        Item itemFirst = new Item("testFirst", "testDescriptionFirst");
        tracker.add(itemFirst);
        tracker.delete(itemFirst);
        assertNull(tracker.findById(itemFirst.getIdItem()));
    }

    /**
     * Test find all item.
     */
    @Test
    public void whenFindAllItemThenVerifyLengthArray() {
        Tracker tracker = new Tracker();
        Item itemFirst = new Item("testFirst", "testDescriptionFirst");
        tracker.add(itemFirst);
        Item itemSecond = new Item("testSecond", "testDescripitonSecond");
        tracker.add(itemSecond);
        assertThat(tracker.findAll().length, is(2));
    }

    /**
     * Test find item with the specified name.
     */
    @Test
    public void whenFindByNameThenVerifyNameReturnArray() {
        Tracker tracker = new Tracker();
        Item itemFirst = new Item("testFirst", "testDescriptionFirst");
        tracker.add(itemFirst);
        Item itemSecond = new Item("testSecond", "testDescripitonSecond");
        tracker.add(itemSecond);
        assertThat(tracker.findByName("testSecond")[0].getNameItem(), is("testSecond"));
    }

    /**
     * Test find item with the id.
     */
    @Test
    public void whenFindByIdThenVerifiReturnId() {
        Tracker tracker = new Tracker();
        Item itemFirst = new Item("testFirst", "testDescriptionFirst");
        tracker.add(itemFirst);
        String expect = itemFirst.getIdItem();
        assertThat(tracker.findById(itemFirst.getIdItem()).getIdItem(), is(expect));
    }
}