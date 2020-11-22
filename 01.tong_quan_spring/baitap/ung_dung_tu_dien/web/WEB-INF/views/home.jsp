<%--
  Created by IntelliJ IDEA.
  User: beut1
  Date: 22-Nov-20
  Time: 5:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Transale English To VietNamese</title>
</head>
<body>
<form action="/findDic">
    <div align="center">
        <fieldset>
            <h1> Translate English To VietNamese</h1>
            <h5>những từ có thể dịch</h5>
            <h5>{"today", "is", "monday", "tomorrow", "tuesday","Wednesday","Thursday","Friday", "weak","saturday","Sunday", "sleep"};</h5>
            <label>English: </label>
            <input type="text" name="word" placeholder="nhập từ tiếng anh đi nào" />
            <input type="submit" value="Translate">
        </fieldset>
        <h1 style="background-color: #a600ff">${result}</h1>
    </div>
</form>
</body>
</html>