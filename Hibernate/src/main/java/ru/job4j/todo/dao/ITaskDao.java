package ru.job4j.todo.dao;

import ru.job4j.todo.model.Task;

import java.util.List;

/**
 * Interface ITaskDao.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 21.03.19.
 */
public interface ITaskDao extends IDao<Task, Integer> {
    /**
     * Find all tasks not done.
     * @return list Task.
     */
    List findAllByNotDone();
}