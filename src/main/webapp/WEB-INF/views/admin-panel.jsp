<%@ page import="org.hibernate.SessionFactory" %>
<%@ page import="com.ayesh.webapp.util.HibernateUtil" %>
<%@ page import="org.hibernate.Session" %>
<%@ page import="com.ayesh.webapp.entity.User" %>
<%@ page import="org.hibernate.query.Query" %>
<%@ page import="com.ayesh.webapp.entity.Employee" %>
<%@ page import="java.util.List" %>
<%@ page import="com.ayesh.webapp.entity.Department" %><%--
  Created by IntelliJ IDEA.
  User: TUF
  Date: 8/19/2023
  Time: 11:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Admin Panel</h1>

<button onclick="goRegisterEmployee()">Register Employee</button>
<br/>
<br/>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Hire Date</th>
        <th>Name</th>
        <th>Salary</th>
        <th>Department</th>
        <th>Position</th>
        <th>Delete</th>
        <th>Edit</th>
    </tr>
    <%

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session sessionData = sessionFactory.openSession();

        List<Employee> employees = sessionData.createQuery("select e from Employee e", Employee.class).getResultList();
        pageContext.setAttribute("employeeDetails", employees);


    %>

    <c:forEach var="employee" items="${employeeDetails}">
        <tr>
            <td>${employee.id}</td>
            <td>${employee.hire_date}</td>
            <td>${employee.name}</td>
            <td>Rs. ${employee.salary}</td>
            <td>${employee.department.name}</td>
            <td>${employee.employeePosition.name}</td>
            <td><button onclick="deleteEmployee(${employee.id})">Delete</button></td>
            <td><button onclick="editEmployee(${employee.id})">Edit</button></td>
        </tr>

    </c:forEach>

</table>
<script src="./js/script.js"></script>
</body>
</html>
