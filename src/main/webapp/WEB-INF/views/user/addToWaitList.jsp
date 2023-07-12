<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Appointment maker</title>
</head>
<body>
<h3>Add me to waiting list</h3>

<h5>Please, fill the form below so we can notify when needed</h5>

For which procedure would you like to make an appointment?

<form id="reservationForm" action="/user/createappointment/towait" method="post">
    <select id="procedureId" name="procedureId">
        <option value="" disabled selected hidden>Choose treatment</option> <!-- Pusta opcja z atrybutem disabled -->
    </select>
    <br>
    <br>
    Choose employee to perform procedure
    <br>
    <select id="employeeId" name="employeeId">
        <option value="" disabled selected hidden>Choose employee</option> <!-- Pusta opcja z atrybutem disabled -->
    </select>
    <br>
    <br>
    Choose the most suitable day for your visit
    <select id="scheduleId" name="scheduleId">
        <option value="" disabled selected hidden>Choose day</option> <!-- Pusta opcja z atrybutem disabled -->
    </select>
    <br>
    <br>
    What time that is currently unavailable would be the best for you?
    <select id="hour" name="hour">
        <option value="" disabled selected hidden>Choose time</option> <!-- Pusta opcja z atrybutem disabled -->
    </select>

    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <input type="hidden" name="userId" value="${userId}"/>
    <button type="submit">Notify me</button>

</form>



<script src="/js/waitingList.js"></script>
</body>
</html>