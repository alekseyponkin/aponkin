<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Update user</title>
</head>
<body>
<h1>Update user <c:out value="${user.name}"></c:out></h1>
<form action = "${pageContext.servletContext.contextPath}/" method = "post">

    <p><b>Name</b><br>
        <input type = "hidden" name = "action" value = "update">
        <input type = "hidden" name = "id" value = "<c:out value="${user.id}"></c:out>">
        <input type = "text" name = "name" value = "<c:out value="${user.name}"></c:out>"><br>

    <p><b>Login</b><br>
    <input type = "text" name = "login" value = "<c:out value="${user.login}"></c:out>"><br>

    <p><b>Password</b><br>
    <input type = "password" name = "password" value = "<c:out value="${user.password}"></c:out>"><br>

    <p><b>Email</b><br>
    <input type = "email" name = "email" value = "<c:out value="${user.email}"></c:out>"><br>

    <c:if test="${role.name == 'user'}">
    <input type = "hidden" name = "roleName" value = "<c:out value="${user.role.name}"></c:out>">
    </c:if>

    <c:if test="${role.name == 'admin'}">
        <p><b>Enter new role for user</b></p>
        <select name = "roleName">
            <c:forEach items="${roles}" var="role">
                <c:if test="${role == user.role.name}">
                    <option selected="selected" value="<c:out value="${role.name}"></c:out>"><c:out value="${role.name}"></c:out></option>
                </c:if>
                <c:if test="${role != user.role.name}">
                    <option value="<c:out value="${role.name}"></c:out>"><c:out value="${role.name}"></c:out></option>
                </c:if>
            </c:forEach>
        </select>
    </c:if>
    <br>
    <p><input type = "submit" value = "Update"></p>
</form>
<a href = "${pageContext.servletContext.contextPath}/singout">Sing Out</a>
</body>
</html>