<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List student</title>
</head>
<body>
    <a href="/student/create">Go create student</a>
    <p style="color: green">${messageSuccess}</p>
    <table border="1">
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Date of birth</th>
            <th>Action</th>
            <th>Action (Path)</th>
        </tr>
        <c:forEach var="student" items="${listStudent}">
            <tr>
                <td>${student.id}</td>
                <td>${student.name}</td>
                <td>${student.dateOfBirth}</td>
                <td><a href="/student/detail?id=${student.id}">Detail student</a></td>
                <td><a href="/student/detail/${student.id}">Detail student</a></td>
            </tr>
        </c:forEach>

    </table>
</body>
</html>
