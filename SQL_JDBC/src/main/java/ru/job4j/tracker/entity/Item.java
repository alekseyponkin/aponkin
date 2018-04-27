package ru.job4j.tracker.entity;

import java.sql.Date;
import java.util.ArrayList;

/**
 * Class Item.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 14.04.2018
 */
public class Item {
    /**
     * Id item.
     */
    private Integer idItem;
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
    private Date dateItem;
    /**
     * ArrayList comments item.
     */
    private ArrayList<String> listCommentsItem;

    /**
     * Default constructor.
     */
    public Item() {
    }

    /**
     * Constructor class Item.
     * @param nameItem name item.
     * @param descriptionItem description item.
     */
    public Item(String nameItem, String descriptionItem) {
        this.nameItem = nameItem;
        this.descriptionItem = descriptionItem;
        this.dateItem = new Date(System.currentTimeMillis());
    }

    /**
     * @param idItem id item.
     */
    public void setIdItem(Integer idItem) {
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
    public void setDateItem(Date dateItem) {
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
    public Integer getIdItem() {
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
    public Date getDateItem() {
        return this.dateItem;
    }

    /**
     * @return ArrayList comments items.
     */
    public ArrayList<String> getListCommentsItem() {
        return this.listCommentsItem;
    }
}