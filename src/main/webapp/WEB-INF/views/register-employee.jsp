<%@ page import="com.ayesh.webapp.entity.Employee" %>
<%@ page import="org.hibernate.SessionFactory" %>
<%@ page import="com.ayesh.webapp.util.HibernateUtil" %>
<%@ page import="org.hibernate.Session" %>
<%@ page import="java.util.List" %>
<%@ page import="com.ayesh.webapp.entity.Department" %>
<%@ page import="com.ayesh.webapp.entity.EmployeePosition" %><%--
  Created by IntelliJ IDEA.
  User: TUF
  Date: 8/21/2023
  Time: 7:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h1>Register Employee</h1>
<br>

<%

    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    Session sessionData = sessionFactory.openSession();

    List<Department> departments = sessionData.createQuery("select depart from Department depart", Department.class).getResultList();
    pageContext.setAttribute("department", departments);

    List<EmployeePosition> employeePositions = sessionData.createQuery("select e_position from employee_position e_position", EmployeePosition.class).getResultList();
    pageContext.setAttribute("employee_position", employeePositions);


%>
<table>
    <tr>
        <td>Name</td>
        <td><input type="text" id="emp_name"></td>
    </tr>

    <tr>
        <td>Salary</td>
        <td><input type="text" id="emp_salary"></td>
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
            <button onclick="registerEmployee()">Register</button>
        </td>
    </tr>
</table>

<script src="./js/script.js"></script>
</body>
</html>
