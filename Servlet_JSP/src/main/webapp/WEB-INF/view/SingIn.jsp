<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>SingIn</title>
</head>
<body>
<h1>Sign in with existing account</h1>
<c:if test="${error != null}">
    <div style="background-color: red">
        <c:out value="${error}"/>
    </div>
</c:if>
<form action = "${pageContext.servletContext.contextPath}/login" method = "post">
    <input type = "text" name = "login"> <label>Login</label> <br>
    <br>
    <input type = "password" name = "password"> <label>Password</label> <br>
    <br>
    <input type = "submit" value = "Sing in"><br>
</form>
<a href = "${pageContext.servletContext.contextPath}/create">Sing up</a>
</body>
</html>
