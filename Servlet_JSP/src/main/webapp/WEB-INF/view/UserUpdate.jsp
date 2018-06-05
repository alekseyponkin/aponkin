<%@ page import="ru.job4j.model.User" %>
<%@ page import="ru.job4j.validate.ValidateMemoryService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update user</title>
</head>
<body>
<h1>Update user</h1>
<%User user = ValidateMemoryService.getInstance().findById(Long.parseLong(request.getParameter("id")));%>
<form action = "<%=request.getContextPath()%>/" method = "post">
    <p><b>Name</b><br>
        <input type = "hidden" name = "action" value = "update">
        <input type = "hidden" name = "id" value = "<%=user.getId()%>">
        <input type = "text" name = "name" value = "<%=user.getName()%>"><br>
    <p><b>Login</b><br>
        <input type = "text" name = "login" value = "<%=user.getLogin()%>"><br>
    <p><b>Email</b><br>
        <input type = "email" name = "email" value = "<%=user.getEmail()%>"><br>
        <br>
        <input type = "submit" value = "Update">
</form>
</body>
</html>