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
<%--    <label for="procedure">Treatment:</label>--%>
    <select id="procedure" name="procedure">
        <option value="">Choose treatment</option>
    </select>
    <br>
<br>
    Choose employee to perform procedure
    <br>
<%--    <label for="doctor">Employee:</label>--%>
    <select id="doctor" name="doctor">
        <option value="">Choose employee</option>
    </select>
    <br>
<br>
    Choose the most suitable day for your visit
<%--    <label for="day">Dzień:</label>--%>
    <select id="day" name="day">
        <option value="">Choose day</option>
    </select>
    <br>
<br>
    What time would be the best for you?
<%--    <label for="hour">Godzina:</label>--%>
    <select id="hour" name="hour">
        <option value="">Choose time</option>
    </select>

    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

    <button type="submit">Create my appointment!</button>
</form>

<script src="/js/script.js"></script>
</body>
</html>