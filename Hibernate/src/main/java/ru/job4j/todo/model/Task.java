package ru.job4j.todo.model;

import java.sql.Timestamp;
import java.util.Objects;

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
    private Boolean done = false;

    /**
     * Default constructor.
     */
    public Task() {
    }

    /**
     * Constructor with description and due date.
     * @param description task.
     * @param dueDate task.
     * @param done true if task done.
     */
    public Task(String description, Timestamp dueDate, Boolean done) {
        this.description = description;
        this.dueDate = dueDate;
        this.done = done;
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

    public Boolean isDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(description, task.description) &&
                Objects.equals(dueDate, task.dueDate) &&
                Objects.equals(done, task.done);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, dueDate, done);
    }
}