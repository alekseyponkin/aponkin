package ru.job4j.todo.service;

import ru.job4j.todo.model.Task;

import java.util.List;

/**
 * Interface IServiceTask.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 21.03.19.
 */
public interface IServiceTask {
    /**
     * Find all tasks not done.
     * @return list Task.
     */
    List<Task> findAllByNotDone();

    /**
     * Find task by id.
     * @param id id model.
     * @return model.
     */
    Task findById(Integer id);

    /**
     * Add new task.
     * @param task for adding.
     * @return generated id.
     */
    Integer add(Task task);

    /**
     * Update task.
     * @param task for updating.
     */
    void update(Task task);

    /**
     * Delete task.
     * @param task for deleting.
     */
    void delete(Task task);

    /**
     * Find all task.
     * @return list task.
     */
    List<Task> findAll();
}