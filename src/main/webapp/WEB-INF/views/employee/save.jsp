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

    <div>Specialties: <form:select path="specialties">
        <form:options items="${specialties}" itemValue="id" itemLabel="name"></form:options>
    </form:select></div>


    <div>Main phone number: <form:input path="phoneNumber1"/></div>
    <div>Emergency phone number: <form:input path="phoneNumber2"/></div>

    <div>Performed procedures: <form:select path="performedProcedures">
        <form:options items="${procedures}" itemValue="id" itemLabel="name"></form:options>
    </form:select></div>
    <br>
    <div>Name to be displayed: <form:input path="name"/></div>
    <br>


    <input type="submit" value="Add new employee">
</form:form>

</body>
</html>
