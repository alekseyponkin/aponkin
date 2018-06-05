<%@ page import="ru.job4j.model.User" %>
<%@ page import="ru.job4j.validate.ValidateMemoryService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <% for (User user : ValidateMemoryService.getInstance().findAll()) { %>
    <tr>
        <td> <%=user.getName()%></td>
        <td> <%=user.getLogin()%></td>
        <td> <%=user.getEmail()%></td>
        <td>
            <form action = "<%=request.getContextPath()%>/edit" method = "get">
                <button name = "id" value = "<%=user.getId()%>">Edit</button>
            </form>
        </td>
        <td>
            <form action = "<%=request.getContextPath()%>/" method = "post">
                <input type = "hidden" name = "id" value = "<%=user.getId()%>">
                <button name = "action" value = "delete">Delete</button>
            </form>
        </td>
    </tr>
    <% } %>
</table>
<form>
    <button formaction = "<%=request.getContextPath()%>/create">Add user</button>
</form>
</body>
</html>