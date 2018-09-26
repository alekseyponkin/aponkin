<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Add user</title>
</head>
<body>
<h1>Add user</h1>
<form action = "${pageContext.servletContext.contextPath}/" method = "post">
    <p><b>Name</b><br>
        <input type = "hidden" name = "action" value = "add">
        <input type = "text" name = "name"><br>
    <p><b>Login</b><br>
        <input type = "text" name = "login"><br>
    <p><b>Password</b><br>
        <input type = "password" name = "password"><br>
    <p><b>Email</b><br>
        <input type = "email" name = "email"><br>

    <c:if test="${role.name == 'admin'}">
    <p><b>Enter role for user</b></p>
    <select name = "roleName">
        <c:forEach items="${roles}" var="role">
                <option value="<c:out value="${role.name}"></c:out>"><c:out value="${role.name}"></c:out></option>
        </c:forEach>
    </select>
    </c:if>
    <br>
    <p><input type = "submit" value = "Add"></p>
</form>
</body>
</html>