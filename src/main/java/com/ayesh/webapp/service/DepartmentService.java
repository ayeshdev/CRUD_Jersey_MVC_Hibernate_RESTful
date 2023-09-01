package com.ayesh.webapp.service;

import com.ayesh.webapp.entity.Department;
import com.ayesh.webapp.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class DepartmentService {
    public static Department getDepartmentById(Integer id) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        return session.createQuery("select d from Department d where d.id=:dId", Department.class).setParameter("dId",id).getSingleResult();
    }
}
