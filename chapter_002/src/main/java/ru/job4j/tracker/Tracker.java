package ru.job4j.tracker;

/**
 * Class Tracker.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 17.09.2017
 */
public class Tracker {
    /**
     * Arrays for items.
     */
    private Item[] items = new Item[100];
    /**
     * Count items added.
     */
    private int positionItem = 0;

    /**
     * Add item.
     * @param item to be added.
     * @return added item.
     */
    public Item add(Item item) {
        Item result;
        this.items[this.positionItem] = item;
        result =  this.items[positionItem];
        this.positionItem++;
        return result;
    }

    /**
     * Update parameters item.
     * @param item updated.
     */
    public void update(Item item) {
        for (Item itemTemp: this.items) {
            if (itemTemp != null && item.getIdItem().equals(itemTemp.getIdItem())) {
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
        int position = this.positionItem;
        for (int i = 0; i < position; i++) {
            if (this.items[i] != null && this.items[i].getIdItem().equals(item.getIdItem())) {
                System.arraycopy(this.items, i + 1, this.items, i, position - i);
            }
        }
        this.positionItem--;
    }

    /**
     * Find all items.
     * @return array added items.
     */
    public Item[] findAll() {
        Item[] result = new Item[this.positionItem];
        for (int i = 0; i < this.positionItem; i++) {
            result[i] = this.items[i];
        }
        return result;
    }

    /**
     * Find all items with the specified name.
     * @param key name items.
     * @return array found items with the name.
     */
    public Item[] findByName(String key) {
        int sizeArray = 0;
        int indexArrey = 0;
        for (Item item: this.items) {
            if (item != null && item.getNameItem().equals(key)) {
                sizeArray++;
            }
        }
        Item[] result = new Item[sizeArray];
        for (int i = 0; i < this.positionItem; i++) {
            if (this.items[i].getNameItem().equals(key)) {
                result[indexArrey] = this.items[i];
                indexArrey++;
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
            if (item != null && item.getIdItem().equals(id)) {
                result = item;
                break;
            }
        }
        return result;
    }
}