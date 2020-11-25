<%--
  Created by IntelliJ IDEA.
  User: beut1
  Date: 24-Nov-20
  Time: 4:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Email</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <style>
        div {
            margin-top: 5px;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Settings</h1>
    <form:form class="form-group" action="/update" modelAttribute="email"  method="post">

        <div class="form-group">
            <form:label path="language"> Languages: </form:label>
            <form:select path="language" class="custom-select" id="language">
                <form:option value="Vietnamese">Vietnamese</form:option>
                <form:option value="English">English</form:option>
                <form:option value="Japanese">Japanese</form:option>
                <form:option value="Chinese">Chinese</form:option>
            </form:select>
        </div>

        <div class="form-group">
            <form:label path="pageSize">Page Size: Show</form:label>
            <form:select path="pageSize" class="custom-select" id="pageSize">
                <p>Show</p>
                <form:option value="5">5</form:option>
                <form:option value="10">10</form:option>
                <form:option value="15">15</form:option>
                <form:option value="20">20</form:option>
            </form:select>
            <form:label path="pageSize"> emails per page</form:label>
        </div>

        <div class="form-check">
            <form:label path="spamsFilter" class="form-check-label col-2" > Spams filter: </form:label>
            <form:checkbox path="spamsFilter" value="Enable spams filter" id="spamsFilter" class="form-check-input" /> Enable spams filter

        </div>
        <div class="form-group">
            <form:label path="sign" for="sign">Singature:</form:label>
            <form:textarea class="form-control"  rows="4" cols="25" id="sign" path="sign"/>
        </div>

        <div>
            <button type="submit" class="btn btn-primary" value="Update" >Submit</button>
            <button type="submit" class="btn btn-primary" value="Cancel" >Cancel</button>
        </div>
    </form:form>

</div>


</body>
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</html>