package ru.job4j.todo.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.job4j.todo.dao.TaskDaoHibernateImpl;
import ru.job4j.todo.model.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Class ServiceTaskHibernateImpl.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 21.03.19.
 */
public class ServiceTaskHibernateImpl implements IServiceTask {

    /**
     * Factory session hibernate.
     */
    private final SessionFactory sessionFactory;

    /**
     * Constructor with factory session hibernate.
     * @param sessionFactory of hibernate.
     */
    public ServiceTaskHibernateImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Task> findAllByNotDone() {
        List<Task> result = new ArrayList<>();
        try (Session session = this.sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                result = new TaskDaoHibernateImpl(session).findAllByNotDone();
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
                e.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public Task findById(Integer id) {
        Task result = new Task();
        try (Session session = this.sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                result = new TaskDaoHibernateImpl(session).findById(id);
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
                e.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public Integer add(Task task) {
        Integer result = -1;
        try (Session session = this.sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                result = new TaskDaoHibernateImpl(session).add(task);
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
                e.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public void update(Task task) {
        try (Session session = this.sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                Task oldTask = new TaskDaoHibernateImpl(session).findById(task.getId());
                this.updateParamTask(oldTask, task);
                new TaskDaoHibernateImpl(session).update(oldTask);
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(Task task) {
        try (Session session = this.sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                new TaskDaoHibernateImpl(session).delete(task);
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Task> findAll() {
        List<Task> result = new ArrayList<>();
        try (Session session = this.sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                result = new TaskDaoHibernateImpl(session).findAll();
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * Update param task.
     * @param oldTask task for updating.
     * @param newTask task with new parameters.
     */
    private void updateParamTask(Task oldTask, Task newTask) {
        if (newTask.getDescription() != null) {
            oldTask.setDescription(newTask.getDescription());
        }
        if (newTask.getDueDate() != null) {
            oldTask.setDueDate(newTask.getDueDate());
        }
        if (newTask.isDone() != null) {
            oldTask.setDone(newTask.isDone());
        }
    }
}