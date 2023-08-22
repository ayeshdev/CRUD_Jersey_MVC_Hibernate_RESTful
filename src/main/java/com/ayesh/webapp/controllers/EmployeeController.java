package com.ayesh.webapp.controllers;

import com.ayesh.webapp.dto.EmployeeDTO;
import com.ayesh.webapp.entity.Department;
import com.ayesh.webapp.entity.Employee;
import com.ayesh.webapp.entity.EmployeePosition;
import com.ayesh.webapp.service.DepartmentService;
import com.ayesh.webapp.service.PositionService;
import com.ayesh.webapp.util.HibernateUtil;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import org.glassfish.jersey.server.mvc.Viewable;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@Path("/employee-controller")
public class EmployeeController {

    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    Session session = HibernateUtil.getSessionFactory().openSession();
    Transaction transaction = session.beginTransaction();



    @GET
    public Viewable registerEmployee(){
        return new Viewable("/register-employee");
    }

    @DELETE
    public Response deleteEmployee(EmployeeDTO employeeDTO) {

        int emp_id = employeeDTO.getId();

        Query query = session.createQuery("delete from Employee where id=:emp_id");
        query.setParameter("emp_id", emp_id);
        query.executeUpdate();

        transaction.commit();

        return Response.ok().entity("Employee Deleted Successful!").build();
    }

    @POST
    public Response registerEmployee(EmployeeDTO employeeDTO){

        String name = employeeDTO.getName();
        Double salary = employeeDTO.getSalary();
        String department = employeeDTO.getDepartment();
        String employee_position = employeeDTO.getPosition();

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String date = dateFormat.format(new Date());

        if(name.isEmpty()){
            return Response.status(Response.Status.BAD_REQUEST).entity("Enter Employee Name").build();
        }else if(salary==null){
            return Response.status(Response.Status.BAD_REQUEST).entity("Enter Employee Salary").build();
        }else{

            Employee employee = new Employee();

            EmployeePosition positionById = PositionService.getPositionById(Integer.valueOf(employee_position));
            Department departmentById = DepartmentService.getDepartmentById(Integer.valueOf(department));

            employee.setName(name);
            employee.setSalary(salary);
            employee.setDepartment(departmentById);
            employee.setEmployeePosition(positionById);
            employee.setHire_date(new Date());


            session.save(employee);
            transaction.commit();
            session.close();

            return Response.ok().entity("Employee Registered Successful!").build();
        }

    }
}
