<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!-- Dodatkowe importy -->
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <title></title>
    <%--  <link rel="stylesheet" href="<c:url value='/static/css/style.css'/>">--%>
</head>
<body>
<h3>Successfully logged in!</h3>
<p><a href="/admin/homee">Admin options</a> </p>
<p><a href="/user/loggedin">User options</a> </p>

</p>
</body>

</html>
