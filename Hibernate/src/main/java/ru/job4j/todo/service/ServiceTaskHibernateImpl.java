package ru.job4j.todo.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
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
    SessionFactory sessionFactory;

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
        try(Session session = this.sessionFactory.openSession()) {
            try {
                Transaction transaction = session.beginTransaction();
                result = new TaskDaoHibernateImpl(session).findAllByNotDone();
                System.out.println(result);
                transaction.commit();
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
        try(Session session = this.sessionFactory.openSession()) {
            try {
                Transaction transaction = session.beginTransaction();
                result = new TaskDaoHibernateImpl(session).findById(id);
                transaction.commit();
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
        try(Session session = this.sessionFactory.openSession()) {
            try {
                Transaction transaction = session.beginTransaction();
                result = new TaskDaoHibernateImpl(session).add(task);
                transaction.commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
                e.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public void update(Task task) {
        try(Session session = this.sessionFactory.openSession()) {
            try {
                Transaction transaction = session.beginTransaction();
                new TaskDaoHibernateImpl(session).update(task);
                transaction.commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(Task task) {
        try(Session session = this.sessionFactory.openSession()) {
            try {
                Transaction transaction = session.beginTransaction();
                new TaskDaoHibernateImpl(session).delete(task);
                transaction.commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Task> findAll() {
        List<Task> result = new ArrayList<>();
        try(Session session = this.sessionFactory.openSession()) {
            try {
                Transaction transaction = session.beginTransaction();
                result = new TaskDaoHibernateImpl(session).findAll();
                transaction.commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
                e.printStackTrace();
            }
        }
        return result;
    }
}
