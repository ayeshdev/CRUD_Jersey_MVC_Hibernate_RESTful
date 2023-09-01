<%--
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
    <style>
        table, th, td {
            border: 1px solid black;
        }
    </style>
</head>
<body onload="processChange()">
<h1>Admin Panel</h1>

<button onclick="goRegisterEmployee()">Register Employee</button>

<br/>

<input type="text" id="search_input" onkeyup="processChange()"/>

<div id="table-container">
    <table id="data_table">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Hire Date</th>
            <th>Salary</th>
            <th>Department</th>
            <th>Position</th>
            <th>delete</th>
            <th>update</th>
        </tr>
    </table>
</div>


<script src="./js/script.js"></script>
</body>
</html>
