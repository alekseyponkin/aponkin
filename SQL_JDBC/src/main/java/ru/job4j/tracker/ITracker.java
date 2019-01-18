package ru.job4j.tracker;

import ru.job4j.tracker.entity.Comment;
import ru.job4j.tracker.entity.Item;

import java.util.List;

/**
 * Interface ITracker.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 14.01.2019
 */
public interface ITracker {
    /**
     * Add item.
     * @param item to be added.
     */
    int add(Item item);

    /**
     * Update parameters item.
     * @param item updated.
     */
    void update(Item item);

    /**
     * Delete item.
     * @param item to be deleted.
     */
    void delete(Item item);

    /**
     * Find all items.
     * @return ArrayList added items.
     */
    List<Item> findAll();

    /**
     * Find all items with the specified name.
     * @param key name items.
     * @return ArrayList found items with the name.
     */
    List<Item> findByName(String key);

    /**
     * Find item with the id.
     * @param id item.
     * @return item with needed id.
     */
    Item findById(String id);

    /**
     * Find all comments for item.
     * @param id id item for searching.
     * @return list found the comments.
     */
    List<Comment> findAllComments(String id);

    /**
     * Add new comment for item.
     * @param comment comment for adding.
     * @param id id item to be added comment.
     */
    void addComment(Comment comment, String id);
}