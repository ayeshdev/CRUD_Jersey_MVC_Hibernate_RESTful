package com.ayesh.webapp.service;

import com.ayesh.webapp.entity.Employee;
import com.ayesh.webapp.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class EmployeeService {
    public static List<Employee> getEmployeeByName(String emp_name){

        String name = "%"+emp_name+"%";

        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Employee> employeeList =  session.createQuery("select e from Employee e where e.name like :name", Employee.class).setParameter("name",name).getResultList();
        return employeeList;
    }

}
