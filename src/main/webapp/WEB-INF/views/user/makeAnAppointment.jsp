<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Appointment maker</title>
</head>
<body>
<h3>Welcome to appointment maker!</h3>

<h5>Appointment reservation form</h5>

For which procedure would you like to make an appointment?

<form id="reservationForm" action="/user/createappointment/apposave" method="post">
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
    What time would be the best for you?
    <select id="hour" name="hour">
        <option value="" disabled selected hidden>Choose time</option> <!-- Pusta opcja z atrybutem disabled -->
    </select>

    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <input type="hidden" name="userId" value="${userId}"/>
    <button type="submit">Create my appointment!</button>

    <div>
        There is no suitable hour available? Fill the form below and wait to be notified via email in case desired time is available.


    </div>
</form>

<script src="/js/script.js"></script>
</body>
</html>
