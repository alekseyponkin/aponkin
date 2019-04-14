package ru.job4j.todo.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.job4j.todo.dao.TaskDaoHibernateImpl;
import ru.job4j.todo.model.Task;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

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
        return this.txFunction(
                session -> new TaskDaoHibernateImpl(session).findAllByNotDone()
        );
    }

    @Override
    public Task findById(Integer id) {
        return this.txFunction(
                session -> new TaskDaoHibernateImpl(session).findById(id)
        );
    }

    @Override
    public Integer add(Task task) {
        return this.txFunction(
                session -> new TaskDaoHibernateImpl(session).add(task)
        );
    }

    @Override
    public void update(Task task) {
        Task oldTask = this.findById(task.getId());
        this.updateParamTask(oldTask, task);
        this.txConsumer(
                session -> new TaskDaoHibernateImpl(session).update(oldTask)
        );
    }

    @Override
    public void delete(Task task) {
        this.txConsumer(
                session -> new TaskDaoHibernateImpl(session).delete(task)
        );
    }

    @Override
    public List<Task> findAll() {
        return this.txFunction(
                session -> new TaskDaoHibernateImpl(session).findAll()
        );
    }

    private <T> T txFunction(final Function<Session, T> command) {
        final Session session = this.sessionFactory.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            return command.apply(session);
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            tx.commit();
            session.close();
        }
    }

    private void txConsumer(final Consumer<Session> command) {
        final Session session = this.sessionFactory.openSession();
        final Transaction tx = session.beginTransaction();
        try {
             command.accept(session);
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            tx.commit();
            session.close();
        }
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