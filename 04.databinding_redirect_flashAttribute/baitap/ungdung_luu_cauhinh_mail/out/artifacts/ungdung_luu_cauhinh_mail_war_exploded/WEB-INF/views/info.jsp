<%--
  Created by IntelliJ IDEA.
  User: beut1
  Date: 24-Nov-20
  Time: 4:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Info Settings</title>
    <style>
        h3{
            color: #4f8000;
        }
    </style>
</head>
<body>
<h1>Info Settings</h1>
<table class="table">
    <thead>
    <tr>
        <th>Languages</th>
        <th>Page Size</th>
        <th>Spams Filter</th>
        <th>Signature</th>

    </tr>
    </thead>
    <tbody>
    <tr>
        <td scope="row"></td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td scope="row">${email1.language}</td>
        <td><${pageSize}/td>
        <td>${spamsFilter}</td>
        <td>${sign}</td>
    </tr>
    </tbody>
</table>
<%--<h1>Info Settings</h1>--%>
<%--<h3>Languages ${email1.language}</h3>--%>
<%--<h3>Page Size: Show ${pageSize} emails per page</h3>--%>
<%--<h3>Spams Filter: ${spamsFilter}</h3>--%>
<%--<h3>Signature: ${sign}</h3>--%>
</body>
</html>
