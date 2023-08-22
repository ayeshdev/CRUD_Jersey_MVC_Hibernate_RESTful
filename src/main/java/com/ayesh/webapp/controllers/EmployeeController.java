package com.ayesh.webapp.controllers;

import com.ayesh.webapp.dto.EmployeeDTO;
import com.ayesh.webapp.entity.Department;
import com.ayesh.webapp.entity.Employee;
import com.ayesh.webapp.entity.EmployeePosition;
import com.ayesh.webapp.service.DepartmentService;
import com.ayesh.webapp.service.PositionService;
import com.ayesh.webapp.util.HibernateUtil;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.glassfish.jersey.server.mvc.Viewable;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Date;
import java.util.List;


@Path("/employee-controller")
public class EmployeeController {
    Session session = HibernateUtil.getSessionFactory().openSession();
    Transaction transaction = session.beginTransaction();



    @GET
    public Viewable registerEmployee(){
        return new Viewable("/register-employee");
    }

    @GET
    @Path("/edit-employee")
    public Viewable goToEditEmployee(@QueryParam("id") String id){
        return new Viewable("/edit-employee",id);
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


    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editEmployee(EmployeeDTO employeeDTO) {

        Integer id = employeeDTO.getId();
        String name = employeeDTO.getName();
        Double salary = employeeDTO.getSalary();
        String department = employeeDTO.getDepartment();
        String employee_position = employeeDTO.getPosition();


        if(name.isEmpty()){
            return Response.status(Response.Status.BAD_REQUEST).entity("Enter Employee Name").build();
        }else if(salary==null){
            return Response.status(Response.Status.BAD_REQUEST).entity("Enter Employee Salary").build();
        }else{

//            Employee employee = session.createQuery("select e from Employee e where e.id=:emp_id", Employee.class).setParameter("emp_id",id).getSingleResult();

//            List<Department> departments = session.createQuery("select depart from Department depart", Department.class).getResultList();

//            List<EmployeePosition> employeePositions = session.createQuery("select e_position from employee_position e_position", EmployeePosition.class).getResultList();


            Employee employee = session.find(Employee.class,id);
            System.out.println(employee);


            EmployeePosition positionById = PositionService.getPositionById(Integer.valueOf(employee_position));
            Department departmentById = DepartmentService.getDepartmentById(Integer.valueOf(department));

            employee.setName(name);
            employee.setSalary(salary);
            employee.setDepartment(departmentById);
            employee.setEmployeePosition(positionById);
            employee.setHire_date(new Date());

//            session.save(employee);
            transaction.commit();
            session.close();

            return Response.ok().entity("Employee Registered Successful!").build();
        }

    }
}
