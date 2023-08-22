package com.ayesh.webapp.service;

import com.ayesh.webapp.entity.EmployeePosition;
import com.ayesh.webapp.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class PositionService {
    public static EmployeePosition getPositionById(Integer id){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        System.out.println("Integer ID of Position Service" + id);

        return session.createQuery("select ep from employee_position ep where ep.id=:pid", EmployeePosition.class).setParameter("pid",id).getSingleResult();
    }
}
