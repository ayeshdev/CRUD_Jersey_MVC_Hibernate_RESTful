package com.ayesh.webapp.service;

import com.ayesh.webapp.entity.User;
import com.ayesh.webapp.model.UserDetails;
import com.ayesh.webapp.util.HibernateUtil;
import org.hibernate.Session;

public class UserService {
    public UserDetails getUserByMobile(String mobile){
        Session session = HibernateUtil.getSessionFactory().openSession();
        User user = session.createQuery("select u from User u where u.mobile=:mobile", User.class).setParameter("mobile",mobile).uniqueResult();
        return new UserDetails(user.getMobile(), user.getPassword(),user.getUserType().toString());
    }
}
