<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Appointment maker</title>
</head>
<body>Welcome to appointment maker!</h1>

For which procedure would you like to make an appointment?
<br>
<div><form:select  path="procedureToChoose">
    <form:options items="${procedures}" itemValue="id" itemLabel="name"></form:options>
</form:select></div>

To which person would you like to go?

<div><form:select  path="employeeToChoose">
    <form:options items="${procedures}" itemValue="id" itemLabel="name"></form:options>
</form:select></div>

What time suits you best?

<div><form:select  path="employeesSchedule">
    <form:options items="${scheduleItems}" itemValue="id" itemLabel="name"></form:options>
</form:select></div>


<form:hidden path="id"/>
<input type="submit" value="Update user">


</body>
</html>
