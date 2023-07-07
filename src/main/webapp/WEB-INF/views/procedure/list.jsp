<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>All categories</title>
</head>
<body>

<table>
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Duration(min)</th>
        <th>Cost</th>
        <th>Category</th>
        <th>Employees entitled to perform</th>
    </tr>
    <c:forEach items="${procedures}" var="procedure">
        <tr>
            <td>${procedure.id}</td>
            <td>${procedure.name}</td>
            <td>${procedure.duration}</td>
            <td>${procedure.cost}</td>
            <td>${procedure.category}</td>
            <td>
                <c:forEach items="${procedure.employeesPerformingProcedure}" var="employee">
                    ${employee.name}<br/>
                </c:forEach>
            </td>


            <td><a href="<c:url value='/admin/procedure/edit?id=${procedure.id}'/>">Edit</a></td>
            <td><a href="<c:url value='/admin/procedure/remove?id=${procedure.id}'/>"
                   onclick="return confirm('Are you sure?')">Remove</a></td>
        </tr>
    </c:forEach>
</table>

<br>
<br>
<td><a href="<c:url value='/admin/procedure/save' />">Add new procedure</a></td>
<br>
<br>
<td><a href="<c:url value='/admin/home' />">Go back home</a></td>

</body>
</html>