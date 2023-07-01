<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Edit schedule item</title>
<%--  <link rel="stylesheet" href="<c:url value='/static/css/style.css'/>">--%>
</head>
<body>

<h3>Edit schedule item</h3>

<form:form method="post" action="edited" modelAttribute="schedule">

    <div>Date: (yyyy-mm-dd) <form:input path="date"/></div>
    <div>Start time: (hh:mm:ss) <form:input path="startTime"/></div>
    <div>End time: (hh:mm:ss) <form:input path="endTime"/></div>
    <div>Date to be displayed: <form:input path="name"/></div>

<form:hidden path="id"/>
    <input type="submit" value="Update schedule item">

</form:form>

</body>
</html>
