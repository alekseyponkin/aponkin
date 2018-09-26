<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>All users</title>
</head>
<body>
<h1>Hello <c:out value="${name}"></c:out>!</h1>
<table>
    <tr>
        <td>Name</td>
        <td>Login</td>
        <td>Email</td>
        <c:if test="${role.name == 'admin'}">
            <td>Role</td>
        </c:if>
    </tr>
    <c:forEach items="${users}" var="user">
    <tr>
        <td> <c:out value="${user.name}"></c:out></td>
        <td> <c:out value="${user.login}"></c:out></td>
        <td> <c:out value="${user.email}"></c:out></td>
        <c:if test="${role.name == 'admin'}">
            <td> <c:out value="${user.role}"></c:out></td>
        </c:if>
        <td>
            <form action = "${pageContext.servletContext.contextPath}/edit" method = "get">
                <button name = "id" value = "<c:out value="${user.id}"></c:out>">Edit</button>
            </form>
        </td>
        <td>
            <form action = "${pageContext.servletContext.contextPath}/" method = "post">
                <input type = "hidden" name = "id" value = "<c:out value="${user.id}"></c:out>">
                <button name = "action" value = "delete">Delete</button>
            </form>
        </td>
    </tr>
    </c:forEach>
</table>
<form>
    <c:if test="${role.name == 'admin'}">
        <button formaction = "${pageContext.servletContext.contextPath}/create">Add user</button>
    </c:if>
</form>
<a href = "${pageContext.servletContext.contextPath}/singout">Sing out</a>
</body>
</html>