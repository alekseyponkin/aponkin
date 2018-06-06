<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Update user</title>
</head>
<body>
<h1>Update user</h1>
<form action = "${pageContext.servletContext.contextPath}/" method = "post">
    <c:set value="${user}" var="user"></c:set>
    <p><b>Name</b><br>
        <input type = "hidden" name = "action" value = "update">
        <input type = "hidden" name = "id" value = "<c:out value="${user.id}"></c:out>">
        <input type = "text" name = "name" value = "<c:out value="${user.name}"></c:out>"><br>
    <p><b>Login</b><br>
        <input type = "text" name = "login" value = "<c:out value="${user.login}"></c:out>"><br>
    <p><b>Email</b><br>
        <input type = "email" name = "email" value = "<c:out value="${user.email}"></c:out>"><br>
        <br>
        <input type = "submit" value = "Update">
</form>
</body>
</html>