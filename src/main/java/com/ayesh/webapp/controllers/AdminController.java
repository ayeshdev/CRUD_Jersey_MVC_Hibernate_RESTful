package com.ayesh.webapp.controllers;

import com.ayesh.webapp.dto.EmployeeDTO;
import com.ayesh.webapp.entity.Employee;
import com.ayesh.webapp.service.EmployeeService;
import com.ayesh.webapp.util.HibernateUtil;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.glassfish.jersey.server.mvc.Viewable;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

@Path("/admin-panel")
public class AdminController {

    Session session = HibernateUtil.getSessionFactory().openSession();
    Transaction transaction = session.beginTransaction();
    @GET
    public Viewable adminPanel(@QueryParam("name") String name){
        return new Viewable("/admin-panel",name);
    }


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserDetails(EmployeeDTO employeeDTO) {

        String name = employeeDTO.getName();

        List<Employee> employeeService = EmployeeService.getEmployeeByName(name);
        System.out.println(employeeService.size());

        ArrayList<EmployeeDTO> employeeDTOArrayList = new ArrayList<>();

        for (int i = 0; i < employeeService.size(); i++) {

            System.out.println("Eren Yeager count : " + i);

            EmployeeDTO dto = new EmployeeDTO();

            dto.setId(employeeService.get(i).getId());
            dto.setName(employeeService.get(i).getName());
            dto.setHire_date(employeeService.get(i).getHire_date().toString());
            dto.setDepartment(employeeService.get(i).getDepartment().getName());
            dto.setPosition(employeeService.get(i).getEmployeePosition().getName());
            dto.setSalary(employeeService.get(i).getSalary());

            employeeDTOArrayList.add(dto);
        }
        return Response.ok().entity(employeeDTOArrayList).build();
    }
}
