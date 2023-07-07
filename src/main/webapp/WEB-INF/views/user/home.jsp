<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!-- Dodatkowe importy -->
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <title>Update user</title>
    <%--  <link rel="stylesheet" href="<c:url value='/static/css/style.css'/>">--%>
</head>
<body>
<h2>Witaj, co chcesz zrobic? </h2>
<p><a href="/user/createappointment/appointments">Umow sie na wizyte</a> </p>
<p><a href="/user/displayme">Zobacz moje dane</a> </p>
<p><a href="/user/editmydata?userId=${user.id}">Edytuj moje dane</a> </p>
<p><a href="/user/createappointment/appointments">Przeglad moich wizyt</a> </p>
<p><a href="/logout">Wyloguj</a> </p>
<%--<form:hidden path="id"/>--%>
</body>

</html>
