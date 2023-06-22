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

            <c:forEach var="scheduleItem" items="${employee.schedule}">
                <p>${scheduleItem}</p>
            </c:forEach>


            <td><a href="<c:url value='/admin/emp/edit?id=${employee.id}'/>">Edit</a></td>
            <td><a href="<c:url value='/admin/emp/remove?id=${employee.id}'/>"
                   onclick="return confirm('Are you sure?')">Remove</a></td>
            <td><a href="<c:url value='/emp/schedule/save?id=${employee.id}' />">Add position to schedule</a></td>
        </tr>
    </c:forEach>
</table>
<td><a href="<c:url value='/admin/emp/save' />">Add new employee</a></td>
<%--<a href="<c:url value='/author/add'/>">Add new author</a>--%>
<%--<a href="<c:url value='/author/search'/>">Search author</a>--%>
<%--<a href="<c:url value='/author/list'/>">List of all authors</a>--%>
</body>
</html>