package ru.job4j.todo.model;

import java.sql.Timestamp;

/**
 * Class Task.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 18.03.19.
 */
public class Task {
    /**
     * Id task.
     */
    private Integer id;
    /**
     * Description task.
     */
    private String description;
    /**
     * Due date for the task.
     */
    private Timestamp dueDate;
    /**
     * True if task done.
     */
    private Boolean done;

    /**
     * Default constructor.
     */
    public Task() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getDueDate() {
        return dueDate;
    }

    public void setDueDate(Timestamp dueDate) {
        this.dueDate = dueDate;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }
}