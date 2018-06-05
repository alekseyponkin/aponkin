<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add user</title>
</head>
<body>
<h1>Add user</h1>
<form action = "<%=request.getContextPath()%>/" method = "post">
    <p><b>Name</b><br>
        <input type = "hidden" name = "action" value = "add">
        <input type = "text" name = "name"><br>
    <p><b>Login</b><br>
        <input type = "text" name = "login"><br>
    <p><b>Email</b><br>
        <input type = "email" name = "email"><br>
        <br>
        <input type = "submit" value = "Add">
</form>
</body>
</html>