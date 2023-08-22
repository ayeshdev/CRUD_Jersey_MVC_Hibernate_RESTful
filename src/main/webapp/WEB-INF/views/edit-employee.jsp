<%@ page import="com.ayesh.webapp.util.HibernateUtil" %>
<%@ page import="org.hibernate.SessionFactory" %>
<%@ page import="org.hibernate.Session" %>
<%@ page import="com.ayesh.webapp.entity.Employee" %>
<%@ page import="java.util.List" %>
<%@ page import="jakarta.persistence.Query" %>
<%@ page import="com.ayesh.webapp.entity.EmployeePosition" %>
<%@ page import="com.ayesh.webapp.entity.Department" %><%--
  Created by IntelliJ IDEA.
  User: TUF
  Date: 8/22/2023
  Time: 11:05 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Edit Employee</title>
</head>
<body>
<h1>Edit Employee</h1>
<br>

<%


    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    Session sessionData = sessionFactory.openSession();

    String id = request.getParameter("id");

    Employee employee = sessionData.createQuery("select e from Employee e where e.id=:emp_id", Employee.class).setParameter("emp_id",id).getSingleResult();
    pageContext.setAttribute("employees", employee);

    List<Department> departments = sessionData.createQuery("select depart from Department depart", Department.class).getResultList();
    pageContext.setAttribute("department", departments);

    List<EmployeePosition> employeePositions = sessionData.createQuery("select e_position from employee_position e_position", EmployeePosition.class).getResultList();
    pageContext.setAttribute("employee_position", employeePositions);



%>
<table>
    <tr>
        <td>Name</td>
        <td><input type="text" id="emp_name" value="${employees.name}"></td>
    </tr>

    <tr>
        <td>Salary</td>
        <td><input type="text" id="emp_salary" value="${employees.salary}"></td>
    </tr>

    <tr>
        <td>Department</td>
        <td>
            <select id="department_id">
                <c:forEach var="department" items="${department}">
                    <option value="${department.id}">${department.name}</option>
                </c:forEach>
            </select>
        </td>
    </tr>

    <tr>
        <td>Position</td>
        <td>
            <select id="position_id">
                <c:forEach var="employee_position" items="${employee_position}">
                    <option value="${employee_position.id}">${employee_position.name}</option>
                </c:forEach>
            </select>
        </td>
    </tr>

    <tr>
        <td></td>
        <td>
            <button id="save_btn">Save Changes</button>
        </td>
    </tr>
</table>

<script>
    document.getElementById("save_btn").addEventListener('click',()=>{

        let empId = `${employees.id}`;
        let employee_name =  document.getElementById("emp_name").value;
        let employee_salary =  document.getElementById("emp_salary").value;
        let employee_department =  document.getElementById("department_id").value;
        let employee_position =  document.getElementById("position_id").value;

        fetch('../../employee-controller', {
            method: 'put',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                id: empId,
                name:employee_name,
                salary:employee_salary,
                department:employee_department,
                position:employee_position,
            })
        }).then(response => response.text()).then(res => {
            console.log(res)
        });
    })
</script>
</body>
</html>
