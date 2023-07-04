<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>All employees</title>
</head>
<body>
<h1>All employees</h1>
<table>
    <tr>
        <th>Id</th>
        <th>First name</th>
        <th>Last name</th>
        <th>Address</th>
        <th>Phone numbers:</th>
        <th>Specialties:</th>
        <th>Performed procedures:</th>
        <th>Schedule:</th>
        <th>Appointments:</th>
    </tr>
    <c:forEach items="${employees}" var="employee">
        <tr>
            <td>${employee.id}</td>
            <td>${employee.firstName}</td>
            <td>${employee.lastName}</td>
            <td>${employee.street}
                <br>
                    ${employee.houseNumber}
                <br>
                    ${employee.postcode}
                <br>
                    ${employee.city}
            </td>
            <td>${employee.phoneNumber1}
                <br>
                    ${employee.phoneNumber2}
            </td>
            <td>
                <c:forEach var="specialty" items="${employee.specialties}">
                    <p>${specialty.getName()}</p>
                </c:forEach>
            </td>
            <td><c:forEach var="procedure" items="${employee.performedProcedures}">
                <p>${procedure.getName()}</p>
            </c:forEach></td>

            <td><c:forEach var="item" items="${employee.schedule}">
                <p>${item.getDate()}</p>
            </c:forEach></td>


            <td>
                <c:forEach var="schedule" items="${employee.schedule}">
                    <p>${schedule.getScheduledAppointments()}</p>
                    <c:forEach var="appointment" items="${schedule.scheduledAppointments}">
                        <p>${appointment.id}</p>
                        <a href="<c:url value='/user/createappointment/rmv?employeeId=${employee.id}&scheduleId=${schedule.id}&appointmentId=${appointment.id}'/>">Remove Appointment</a>
                    </c:forEach>
                    <a href="<c:url value='/emp/schedule/rmv?employeeId=${employee.id}&scheduleId=${schedule.id}'/>">Remove Schedule</a>
                    <a href="<c:url value='/emp/schedule/edit?employeeId=${employee.id}&scheduleId=${schedule.id}'/>">Edit Schedule</a>
                </c:forEach>
            </td>

        </tr>
        </td>


        <td><a href="<c:url value='/admin/emp/edit?id=${employee.id}'/>">Edit</a></td>
        <td><a href="<c:url value='/emp/schedule/remove?id=${employee.id}'/>"
               onclick="return confirm('Are you sure?')">Remove</a></td>
        <td><a href="<c:url value='/emp/schedule/save?id=${employee.id}'/>">Add position to schedule</a></td>

        </tr>

        <form:hidden path="id"/>
    </c:forEach>
</table>
<br>
<br>
<td><a href="<c:url value='/admin/home' />">Go back home</a></td>
<br>
<br>
<td><a href="<c:url value='/admin/emp/save' />">Add new employee</a></td>
</body>
</html>