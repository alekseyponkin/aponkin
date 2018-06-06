<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>All users</title>
</head>
<body>
<h1>All users</h1>
<table>
    <tr>
        <td>Name</td>
        <td>Login</td>
        <td>Email</td>
    </tr>
    <c:forEach items="${users}" var="user">
    <tr>
        <td> <c:out value="${user.name}"></c:out></td>
        <td> <c:out value="${user.login}"></c:out></td>
        <td> <c:out value="${user.email}"></c:out></td>
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
    <button formaction = "${pageContext.servletContext.contextPath}/create">Add user</button>
</form>
</body>
</html>