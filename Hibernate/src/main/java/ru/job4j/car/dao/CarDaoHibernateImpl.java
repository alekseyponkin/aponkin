package ru.job4j.car.dao;

import org.hibernate.Session;
import ru.job4j.car.model.Car;

import java.util.List;

/**
 * Class CarDaoHibernateImpl.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 16.04.19.
 */
public class CarDaoHibernateImpl implements ICarDao {

    /**
     * Session of hibernate.
     */
    private final Session session;

    /**
     * Constructor with session of hibernate.
     * @param session of hibernate.
     */
    public CarDaoHibernateImpl(Session session) {
        this.session = session;
    }

    @Override
    public Car findById(Long id) {
        return this.session.get(Car.class, id);
    }

    @Override
    public Long add(Car car) {
        return (Long) this.session.save(car);
    }

    @Override
    public void update(Car car) {
        this.session.update(car);
    }

    @Override
    public void delete(Car car) {
        this.session.delete(car);
    }

    @Override
    public List<Car> findAll() {
        return this.session.createQuery("from Car").list();
    }
}