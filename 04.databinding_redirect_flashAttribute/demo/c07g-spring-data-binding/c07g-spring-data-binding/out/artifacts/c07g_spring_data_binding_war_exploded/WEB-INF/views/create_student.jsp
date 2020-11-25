<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create student</title>
</head>
<body>
    <form:form action="/student/create" modelAttribute="studentObj" method="post">
        <p>Name: </p>
        <form:input type="text" path="name"  />
        <p>Date of birth: </p>
        <form:input type="date" path="dateOfBirth" />

        <input type="submit" value="Save" />
    </form:form>
</body>
</html>
