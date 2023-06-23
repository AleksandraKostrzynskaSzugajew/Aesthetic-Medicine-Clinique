<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>All users</title>
</head>
<body>
<h1>All users</h1>
<table>
    <tr>
        <th>Id</th>
        <th>Email</th>
        <th>First name</th>
        <th>Last name</th>
        <th>Date of birth</th>

        <th>Address</th>
        <th>Phone numbers</th>

    </tr>
    <c:forEach items="${users}" var="user">
        <tr>
            <td>${user.id}</td>
            <td>${user.firstName}</td>
            <td>${user.lastName}</td>
            <td>${user.dob}</td>
            <td>${user.street}
                <br>
                    ${user.houseNumber}
                <br>
                    ${user.postcode}
                <br>
                    ${user.city}
            </td>
            <td>${user.phoneNumber1}
                <br>
                    ${user.phoneNumber2}

            <td><a href="<c:url value='/user/edit?id=${user.id}'/>">Edit</a></td>
            <td><a href="<c:url value='/user/remove?id=${user.id}'/>"
                   onclick="return confirm('Are you sure?')">Remove</a></td>
        </tr>

        <form:hidden path="id"/>
    </c:forEach>
</table>
<td><a href="<c:url value='/user/save' />">Add new user</a></td>
</body>
</html>