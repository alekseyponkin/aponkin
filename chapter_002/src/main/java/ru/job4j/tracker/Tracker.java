package ru.job4j.tracker;

import java.util.ArrayList;

/**
 * Class Tracker.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 17.09.2017
 */
public class Tracker {
    /**
     * ArrayList for items.
     */
    private ArrayList<Item> items = new ArrayList<>();

    /**
     * Add item.
     * @param item to be added.
     */
    public void add(Item item) {
        this.items.add(item);

    }

    /**
     * Update parameters item.
     * @param item updated.
     */
    public void update(Item item) {
        for (Item itemTemp: this.items) {
            if (item.getIdItem().equals(itemTemp.getIdItem())) {
                itemTemp.setNameItem(item.getNameItem());
                itemTemp.setDescriptionItem(item.getDescriptionItem());
                itemTemp.setDateItem(item.getDateItem());
                itemTemp.setListCommentsItem(item.getListCommentsItem());
            }
        }
    }

    /**
     * Delete item.
     * @param item to be deleted.
     */
    public void delete(Item item) {
        this.items.remove(item);
    }

    /**
     * Find all items.
     * @return ArrayList added items.
     */
    public ArrayList<Item> findAll() {
        return this.items;
    }

    /**
     * Find all items with the specified name.
     * @param key name items.
     * @return ArrayList found items with the name.
     */
    public ArrayList<Item> findByName(String key) {
        ArrayList<Item> result = new ArrayList<>();
        for (Item item: this.items) {
            if (item.getNameItem().equals(key)) {
                result.add(item);
            }
        }
        return result;
    }

    /**
     * Find item with the id.
     * @param id item.
     * @return item with needed id.
     */
    public Item findById(String id) {
        Item result = null;
        for (Item item: this.items) {
            if (item.getIdItem().equals(id)) {
                result = item;
                break;
            }
        }
        return result;
    }
}