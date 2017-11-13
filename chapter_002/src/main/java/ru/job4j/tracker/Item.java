package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.Random;

/**
 * Class Item.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 17.09.2017
 */
public class Item {
    /**
     * Id item.
     */
    private String idItem;
    /**
     * Name item.
     */
    private String nameItem;
    /**
     * Description item.
     */
    private String descriptionItem;
    /**
     * Date create item.
     */
    private long dateItem;
    /**
     * ArrayList comments item.
     */
    private ArrayList<String> listCommentsItem;
    /**
     * Random number for calculate Id item.
     */
    private static final Random RN = new Random();

    /**
     * Constructor class Item.
     * @param nameItem name item.
     * @param descriptionItem description item.
     */
    public Item(String nameItem, String descriptionItem) {
        this.idItem = String.valueOf(System.currentTimeMillis() + RN.nextInt());
        this.nameItem = nameItem;
        this.descriptionItem = descriptionItem;
        this.dateItem = System.currentTimeMillis();
    }

    /**
     * @param idItem id item.
     */
    public void setIdItem(String idItem) {
        this.idItem = idItem;
    }

    /**
     * @param nameItem name item.
     */
    public void setNameItem(String nameItem) {
        this.nameItem = nameItem;
    }

    /**
     * @param descriptionItem description item.
     */
    public void setDescriptionItem(String descriptionItem) {
        this.descriptionItem = descriptionItem;
    }

    /**
     * @param dateItem date create item.
     */
    public void setDateItem(long dateItem) {
        this.dateItem = dateItem;
    }

    /**
     * @param listCommentsItem ArrayList comments item.
     */
    public void setListCommentsItem(ArrayList<String> listCommentsItem) {
        this.listCommentsItem = listCommentsItem;
    }

    /**
     * @return id item.
     */
    public String getIdItem() {
        return this.idItem;
    }

    /**
     * @return name item.
     */
    public String getNameItem() {
        return this.nameItem;
    }

    /**
     * @return description item.
     */
    public String getDescriptionItem() {
        return this.descriptionItem;
    }

    /**
     * @return date create item.
     */
    public long getDateItem() {
        return this.dateItem;
    }

    /**
     * @return ArrayList comments items.
     */
    public ArrayList<String> getListCommentsItem() {
        return this.listCommentsItem;
    }
}
