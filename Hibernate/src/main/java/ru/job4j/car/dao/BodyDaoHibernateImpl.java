package ru.job4j.car.dao;

import org.hibernate.Session;
import ru.job4j.car.model.Body;

import java.util.List;

/**
 * Class BodyDaoHibernateImpl.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 16.04.19.
 */
public class BodyDaoHibernateImpl implements IBodyDao {

    /**
     * Session of hibernate.
     */
    private final Session session;

    /**
     * Constructor with session of hibernate.
     * @param session of hibernate.
     */
    public BodyDaoHibernateImpl(Session session) {
        this.session = session;
    }

    @Override
    public Body findById(Long id) {
        return this.session.get(Body.class, id);
    }

    @Override
    public Long add(Body body) {
        return (Long) this.session.save(body);
    }

    @Override
    public void update(Body body) {
        this.session.update(body);
    }

    @Override
    public void delete(Body body) {
        this.session.delete(body);
    }

    @Override
    public List<Body> findAll() {
        return this.session.createQuery("from Body").list();
    }
}