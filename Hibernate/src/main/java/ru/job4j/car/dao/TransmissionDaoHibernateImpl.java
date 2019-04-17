package ru.job4j.car.dao;

import org.hibernate.Session;
import ru.job4j.car.model.Transmission;

import java.util.List;

/**
 * Class TransmissionDaoHibernateImpl.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 16.04.19.
 */
public class TransmissionDaoHibernateImpl implements ITransmissionDao {

    /**
     * Session of hibernate.
     */
    private final Session session;

    /**
     * Constructor with session of hibernate.
     * @param session of hibernate.
     */
    public TransmissionDaoHibernateImpl(Session session) {
        this.session = session;
    }

    @Override
    public Transmission findById(Long id) {
        return this.session.get(Transmission.class, id);
    }

    @Override
    public Long add(Transmission transmission) {
        return (Long) this.session.save(transmission);
    }

    @Override
    public void update(Transmission transmission) {
        this.session.update(transmission);
    }

    @Override
    public void delete(Transmission transmission) {
        this.session.delete(transmission);
    }

    @Override
    public List<Transmission> findAll() {
        return this.session.createQuery("from Transmission").list();
    }
}