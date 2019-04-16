package ru.job4j.car.dao;

import java.util.List;

/**
 * Interface IDao.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 15.04.19.
 */
public interface IDao<T, K> {
    /**
     * Find model by id.
     * @param id model.
     * @return model.
     */
    T findById(K id);

    /**
     * Add new model.
     * @param model for adding.
     * @return generated id.
     */
    K add(T model);

    /**
     * Update model.
     * @param model
     */
    void update(T model);

    /**
     * Delete model.
     * @param mode for deleting.
     */
    void delete(T mode);

    /**
     * Find all model.
     * @return list models.
     */
    List<T> findAll();
}