package ru.job4j.car;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.AfterClass;
import org.junit.Test;
import ru.job4j.car.dao.*;
import ru.job4j.car.model.Body;
import ru.job4j.car.model.Car;
import ru.job4j.car.model.Engine;
import ru.job4j.car.model.Transmission;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Class CarDaoXmlHibernateImplTest.
 *
 * @author Ponkin Aleksey
 * @version 1.0.0
 * @since 17.04.19.
 */
public class CarDaoXmlHibernateImplTest {
    /**
     * Session factory of hibernate mapping on xml.
     */
    private final static SessionFactory FACTORY = new Configuration().configure("carXmlHibernate.cfg.xml").buildSessionFactory();

    @Test
    public void whenAddCarThenCheckInDb() {
        try (Session session = FACTORY.openSession()) {
            session.beginTransaction();
            IDao daoEngine = new EngineDaoHibernateImpl(session);
            Engine engine = new Engine("1.6 MPI");
            daoEngine.add(engine);

            IDao daoBody = new BodyDaoHibernateImpl(session);
            Body body = new Body("hatchback 5dr");
            daoBody.add(body);

            IDao daoTransmission = new TransmissionDaoHibernateImpl(session);
            Transmission transmission = new Transmission("5MT");
            daoTransmission.add(transmission);

            ICarDao daoCar = new CarDaoHibernateImpl(session);
            daoCar.add(new Car("Ceed", engine, transmission, body));
            Car carInDb = daoCar.findAll().get(0);

            assertThat(carInDb.getName(), is("Ceed"));
            assertThat(carInDb.getTransmission().getName(), is("5MT"));
            assertThat(carInDb.getBody().getName(), is("hatchback 5dr"));
            assertThat(carInDb.getEngine().getName(), is("1.6 MPI"));
            session.getTransaction().rollback();
        }
    }

    @Test
    public void whenUpdateCarThenCheckUpdatedCar() {
        try (Session session = FACTORY.openSession()) {
            session.beginTransaction();
            IDao daoEngine = new EngineDaoHibernateImpl(session);
            Engine engine = new Engine("1.6 MPI");
            daoEngine.add(engine);

            IDao daoBody = new BodyDaoHibernateImpl(session);
            Body body = new Body("hatchback 5dr");
            daoBody.add(body);

            IDao daoTransmission = new TransmissionDaoHibernateImpl(session);
            Transmission transmission = new Transmission("5MT");
            daoTransmission.add(transmission);

            ICarDao daoCar = new CarDaoHibernateImpl(session);
            Car car = new Car("Ceed", engine, transmission, body);
            daoCar.add(car);
            Car carInDb = daoCar.findById(car.getId());
            assertThat(carInDb.getEngine().getName(), is("1.6 MPI"));

            Engine newEngine = new Engine("1.4 TSI");
            daoEngine.add(newEngine);
            car.setEngine(newEngine);
            daoCar.update(car);
            assertThat(daoCar.findAll().get(0).getEngine().getName(), is("1.4 TSI"));
            session.getTransaction().rollback();
        }
    }

    @Test
    public void whenDeleteCarFromDbThenCheckInDb() {
        try (Session session = FACTORY.openSession()) {
            session.beginTransaction();
            IDao daoEngine = new EngineDaoHibernateImpl(session);
            Engine engine = new Engine("1.6 MPI");
            daoEngine.add(engine);

            IDao daoBody = new BodyDaoHibernateImpl(session);
            Body body = new Body("hatchback 5dr");
            daoBody.add(body);

            IDao daoTransmission = new TransmissionDaoHibernateImpl(session);
            Transmission transmission = new Transmission("5MT");
            daoTransmission.add(transmission);

            ICarDao daoCar = new CarDaoHibernateImpl(session);
            Car car = new Car("Ceed", engine, transmission, body);
            daoCar.add(car);
            Car carInDb = daoCar.findById(car.getId());
            assertThat(carInDb.getName(), is("Ceed"));

            daoCar.delete(car);
            assertTrue(daoCar.findAll().isEmpty());
            session.getTransaction().rollback();
        }
    }

    @AfterClass
    public static void tearDown() throws Exception {
        FACTORY.close();
    }
}