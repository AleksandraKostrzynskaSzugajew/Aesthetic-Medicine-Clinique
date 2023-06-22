<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Save new employee</title>
    <%--  <link rel="stylesheet" href="<c:url value='/static/css/style.css'/>">--%>
</head>
<body>

<h3>Save new employee</h3>

<form:form method="post" action="saved" modelAttribute="employee">

    <div>First name: <form:input path="firstName"/></div>
    <div>Last name: <form:input path="lastName"/></div>
    <div>Street: <form:input path="street"/></div>
    <div>House number: <form:input path="houseNumber"/></div>
    <div>Post code: <form:input path="postcode"/></div>
    <div>City: <form:input path="city"/></div>
    <div>Specialties: <form:select path="specialties" items="${specialties}" itemLabel="fullName" itemValue="id"
                                   multiple="true"/></div>
    <div>Phone number 1: <input type="text" name="phone1"/></div>
    <div>Phone number 2:<input type="text" name="phone2"/></div>


    <input type="submit" value="Add new category">
</form:form>

</body>
</html>
