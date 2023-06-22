<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Save procedure</title>
<%--  <link rel="stylesheet" href="<c:url value='/static/css/style.css'/>">--%>
</head>
<body>

<h3>Save procedure</h3>

<form:form method="post" action="saved" modelAttribute="procedure">

    <div>Procedure name: <form:input path="name"/></div>
    <div>Procedure duration: <form:input path="duration"/></div>
    <div>Procedure cost: <form:input path="cost"/></div>

    <div>Category:
    <form:select  path="category">
        <form:options items="${categories}" itemValue="id" itemLabel="name"></form:options>
    </form:select></div>
    <div>Employees entitled to perform procedure:     <form:select  path="employeesPerformingProcedure">
        <form:options items="${empForPro}" itemValue="id" itemLabel="name"></form:options>
    </form:select></div>

    <input type="submit" value="Add new procedure">
</form:form>

</body>
</html>
