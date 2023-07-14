<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>


<form method="post" action="login">
    <div><label> User Name : <input type="text" name="username"/> </label></div>
    <div><label> Password: <input type="password" name="password"/> </label></div>
    <div><input type="submit" value="Sign In"/></div>
    <div>
        <br>
        <br>
        <br>
        <a href="/oauth2/authorization/google">Zaloguj przez Google</a>

        <br>
        <br>
        <br>
        Do not have an account yet?
        <a href="<c:url value='/register'/>">Click here to register!</a>
    </div>



    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>
</body>
</html>
