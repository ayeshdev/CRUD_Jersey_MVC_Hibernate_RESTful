package com.ayesh.webapp.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final SessionFactory SESSION_FACTORY = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
//    Configuration configuration = new Configuration();
//    configuration.configure();
//    configuration.buildSessionFactory();
            return new Configuration().configure().buildSessionFactory(); //uda lines 3n karapu tika me line eken wenwa
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ExceptionInInitializerError();
        }
    }

    public static SessionFactory getSessionFactory() {
        return SESSION_FACTORY;
    }

    public static void close() {
        getSessionFactory().close();
    }

}
