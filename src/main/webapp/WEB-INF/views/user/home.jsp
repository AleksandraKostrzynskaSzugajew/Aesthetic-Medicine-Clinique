<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!-- Dodatkowe importy -->
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <title>User menu</title>
    <%--  <link rel="stylesheet" href="<c:url value='/static/css/style.css'/>">--%>
</head>
<body>
<h2>Welcome, what would you like to do? </h2>
<p><a href="/user/createappointment/appointments">Make an appointment</a> </p>
<p><a href="/user/displayme">Show me my personal data</a> </p>
<p><a href="/user/editmydata?userId=${user.id}">Edit my personal data</a> </p>
<p><a href="/user/history">Browse my appointments</a> </p>
<p><a href="/logout">Log out</a> </p>
<%--<form:hidden path="id"/>--%>
</body>

</html>
