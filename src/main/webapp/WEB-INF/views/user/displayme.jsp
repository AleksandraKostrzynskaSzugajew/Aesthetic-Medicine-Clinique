<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>User Details</title>
    <%--  <link rel="stylesheet" href="<c:url value='/static/css/style.css'/>">--%>
</head>
<body>


<h2>User Details</h2>
<table>
    <tr>
        <td><strong>Email:</strong></td>
        <td>${user.email}</td>
    </tr>
    <tr>
        <td><strong>First Name:</strong></td>
        <td>${user.firstName}</td>
    </tr>
    <tr>
        <td><strong>Last Name:</strong></td>
        <td>${user.lastName}</td>
    </tr>
    <tr>
        <td><strong>Date of Birth:</strong></td>
<%--        <td><fmt:formatDate value="${dobString}" pattern="dd-MM-yyyy" /></td>--%>
        <td>${dobString}</td>
    </tr>
    <tr>
        <td><strong>Street:</strong></td>
        <td>${user.street}</td>
    </tr>
    <tr>
        <td><strong>House Number:</strong></td>
        <td>${user.houseNumber}</td>
    </tr>
    <tr>
        <td><strong>Postcode:</strong></td>
        <td>${user.postcode}</td>
    </tr>
    <tr>
        <td><strong>City:</strong></td>
        <td>${user.city}</td>
    </tr>
    <tr>
        <td><strong>Phone Number 1:</strong></td>
        <td>${user.phoneNumber1}</td>
    </tr>
    <tr>
        <td><strong>Phone Number 2:</strong></td>
        <td>${user.phoneNumber2}</td>
    </tr>
    <tr>
        <td><strong>Role:</strong></td>
        <td>${user.role.name}</td>
    </tr>
</table>
</body>
</html>
