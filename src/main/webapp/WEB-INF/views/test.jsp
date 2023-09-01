<%--
  Created by IntelliJ IDEA.
  User: TUF
  Date: 8/20/2023
  Time: 11:16 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        table, th, td {
            border: 1px solid black;
        }
    </style>
</head>
<body>


<input type="text" id="search_input" onkeyup="processChange()"/>
<button onclick="test()">get data</button>

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

<script src="./js/test.js"></script>
</body>
</html>
