<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>My schedule items</title>
</head>
<body>

<table>
    <tr>
        <th>Id</th>
        <th>Date</th>
        <th>Start time</th>
        <th>End time</th>
    </tr>
    <c:forEach items="${scheduleItems}" var="item">
        <tr>
            <td>${item.id}</td>
            <td>${item.date}</td>
            <td>${item.startTime}</td>
            <td>${item.endTime}</td>


            <td><a href="<c:url value='/emp/schedule/edit?id=${item.id}'/>">Edit</a></td>
            <td><a href="<c:url value='/emp/schedule/remove?id=${item.id}'/>" onclick="return confirm('In case of any change you schould notify all appointed patients. Are you sure?')">Remove</a></td>
            <td><a href="<c:url value='/emp/schedule/save' />">Add new schedule item</a></td>
        </tr>
    </c:forEach>
</table>

</body>
</html>