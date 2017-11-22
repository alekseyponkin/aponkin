package ru.job4j.generic;

/**
 * Abstract class AbstractStore.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 21.11.2017
 */
public abstract class AbstractStore<T extends Base> implements Store<T> {
    /**
     * Store for save Base.
     */
    SimpleArray<T> simpleArray;

    /**
     * Constructor AbstractStore.
     * @param size size store.
     */
    public AbstractStore(int size) {
        this.simpleArray = new SimpleArray<>(size);
    }

    @Override
    public T add(T model) {
        simpleArray.add(model);
        return model;
    }

    @Override
    public T update(T model) {
        T result = null;
        int index = indexOf(model.getId());
        if (index != -1) {
            this.simpleArray.update(index, model);
            result = model;
        }
        return result;
    }

    @Override
    public boolean delete(String id) {
        boolean result = false;
        int index = indexOf(id);
        if (index != -1) {
            this.simpleArray.delete(index);
            result = true;
        }
        return result;
    }

    /**
     * Get first index Base in Store.
     * @param id id Base.
     * @return the index or -1 if not contain the id.
     */
    private int indexOf(String id) {
        int result = -1;
        for (int i = 0; i < this.simpleArray.size(); i++) {
            if (this.simpleArray.get(i).getId().equals(id)) {
                result = i;
                break;
            }
        }
        return  result;
    }
}