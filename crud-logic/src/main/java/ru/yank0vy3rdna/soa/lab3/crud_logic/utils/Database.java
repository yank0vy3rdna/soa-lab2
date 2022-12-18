package ru.yank0vy3rdna.soa.lab3.crud_logic.utils;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


@Named
@ApplicationScoped
public class Database {
    private final SessionFactory sessionFactory;

    public Database() {
        Configuration configuration = new Configuration();
        configuration.configure("database.cfg.xml");

        sessionFactory = configuration.buildSessionFactory();
    }

    public Session getSession() {
        return sessionFactory.openSession();
    }
}