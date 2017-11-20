package ru.job4j.generic;

/**
 * Interface Store .
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 20.11.2017
 */
public interface Store<T extends Base> {

    /**
     * Add base in stare.
     * @param model for add.
     * @return adding model.
     */
    T add(T model);

    /**
     * Update base in store.
     * @param model for updating.
     * @return updating model.
     */
    T update(T model);

    /**
     * Delete base in store.
     * @param id base.
     * @return true if deleting success.
     */
    boolean delete(String id);
}