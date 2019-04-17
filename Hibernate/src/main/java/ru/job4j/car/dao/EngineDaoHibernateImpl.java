package ru.job4j.car.dao;

import org.hibernate.Session;
import ru.job4j.car.model.Engine;

import java.util.List;

/**
 * Class EngineDaoHibernateImpl.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 16.04.19.
 */
public class EngineDaoHibernateImpl implements IEngineDao {

    /**
     * Session of hibernate.
     */
    private final Session session;

    /**
     * Constructor with session of hibernate.
     * @param session of hibernate.
     */
    public EngineDaoHibernateImpl(Session session) {
        this.session = session;
    }

    @Override
    public Engine findById(Long id) {
        return this.session.get(Engine.class, id);
    }

    @Override
    public Long add(Engine engine) {
        return (Long) this.session.save(engine);
    }

    @Override
    public void update(Engine engine) {
        this.session.update(engine);
    }

    @Override
    public void delete(Engine engine) {
        this.session.delete(engine);
    }

    @Override
    public List<Engine> findAll() {
        return this.session.createQuery("from Engine").list();
    }
}