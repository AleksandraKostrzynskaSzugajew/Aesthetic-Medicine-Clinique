<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Update user</title>
    <%--  <link rel="stylesheet" href="<c:url value='/static/css/style.css'/>">--%>
</head>
<body>

<h3>Update user</h3>

<form:form method="post" action="editedUser" modelAttribute="user">


    <div>Email: <form:input path="email"/></div>
    <div>Password: <form:input path="password"/></div>
    <div>First name: <form:input path="firstName"/></div>
    <div>Last name: <form:input path="lastName"/></div>
    <div>Date of birth: <form:input path="dob"/></div>
    <div>Street: <form:input path="street"/></div>
    <div>House number: <form:input path="houseNumber"/></div>
    <div>Post code: <form:input path="postcode"/></div>
    <div>City: <form:input path="city"/></div>
    <div>Main phone number: <form:input path="phoneNumber1"/></div>
    <div>Emergency phone number: <form:input path="phoneNumber2"/></div>

    <form:hidden path="id"/>
    <input type="submit" value="Update user">
</form:form>

</body>
</html>
