<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>My Appointment History</title>
</head>
<body>
<h1>My Appointment History</h1>

<h2>Future Appointments:</h2>
<table>
    <tr>
        <th>Date</th>
        <th>Start Time</th>
        <th>End Time</th>
        <th>Procedure</th>
        <th>Cancel Appointment</th>
    </tr>
    <c:forEach var="appointment" items="${future}">
        <tr>
            <td>${appointment.date}</td>
            <td>${appointment.startTime}</td>
            <td>${appointment.endTime}</td>
            <td>${appointment.procedureId}</td>
            <td>
                <form action="createappointment/cancelappointment" method="post">
                    <input type="hidden" name="appointmentId" value="${appointment.appointmentId}" />
                    <button type="submit">Cancel</button>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>

<h2>Past Appointments:</h2>
<table>
    <tr>
        <th>Date</th>
        <th>Start Time</th>
        <th>End Time</th>
        <th>Procedure</th>
    </tr>
    <c:forEach var="appointment" items="${past}">
        <tr>
            <td>${appointment.date}</td>
            <td>${appointment.startTime}</td>
            <td>${appointment.endTime}</td>
            <td>${appointment.procedureId}</td>
        </tr>
    </c:forEach>


</table>
</body>
</html>
