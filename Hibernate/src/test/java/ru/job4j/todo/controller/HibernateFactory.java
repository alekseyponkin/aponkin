package ru.job4j.todo.controller;


import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import static ru.job4j.todo.controller.ConnectionRollback.create;

public class HibernateFactory {
    private static SessionFactory factory =  create(new Configuration().configure().buildSessionFactory());

    public static SessionFactory getInstance() {
        return factory;
    }

    private HibernateFactory() {
    }
}
