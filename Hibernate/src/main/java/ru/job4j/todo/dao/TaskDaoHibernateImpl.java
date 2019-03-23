package ru.job4j.todo.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import ru.job4j.todo.model.Task;

import java.util.List;

/**
 * Class TaskDaoHibernateImpl.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 21.03.19.
 */
public class TaskDaoHibernateImpl implements ITaskDao {

    /**
     * Session of hibernate.
     */
    Session session;

    public TaskDaoHibernateImpl(Session session) {
        this.session = session;
    }

    @Override
    public List<Task> findAllByNotDone() {
        Query<Task> query = this.session.createQuery("from Task where done = :done");
        query.setParameter("done", false);
        return query.getResultList();
    }

    @Override
    public Task findById(Integer id) {
        Query<Task> query = this.session.createQuery("from Task where id = :id");
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public Integer add(Task model) {
       return (Integer) this.session.save(model);
    }

    @Override
    public void update(Task model) {
        this.session.update(model);

    }

    @Override
    public void delete(Task mode) {
        this.session.delete(mode);

    }

    @Override
    public List<Task> findAll() {
        return this.session.createQuery("from Task").list();
    }
}