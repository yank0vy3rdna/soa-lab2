//package ru.yank0vy3rdna.soa.lab3.crud_logic.dao.utils;
//
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.cfg.Configuration;
//
//public final class Database {
//    private static final SessionFactory sessionFactory;
//
//    static {
//        Configuration configuration = new Configuration();
//        configuration.configure("database.cfg.xml");
//
//        sessionFactory = configuration.buildSessionFactory();
//    }
//
//    public static Session getSession() {
//        return sessionFactory.openSession();
//    }
//}