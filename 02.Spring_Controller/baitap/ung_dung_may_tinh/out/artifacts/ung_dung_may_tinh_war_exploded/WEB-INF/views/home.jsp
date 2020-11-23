<%--
  Created by IntelliJ IDEA.
  User: beut1
  Date: 23-Nov-20
  Time: 4:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Caculator</title>
</head>
<body>
<form action="/result">
    <div align="center">
        <fieldset>
            <h1>Calculator</h1>
            <div align="center">
                <input type="text" name="num1" placeholder="Input number 1">
                <input type="text" name="num2" placeholder="Input number 2">
            </div>
            <input type="radio" name="calculate" value="add" id="add"/>Add (+)
            <input type="radio" name="calculate" value="subs" id="subs"/>Subs (-)
            <input type="radio" name="calculate" value="multi" id="multi"/>Multi (*)
            <input type="radio" name="calculate" value="div" id="div"/>Div (/)
            <div align="center">
                <input type="submit" value="Calculate"/>
            </div>
        </fieldset>
        <h3 style="background-color: #bbff00">${calculate}</h3>
    </div>
</form>
</body>
</html>
