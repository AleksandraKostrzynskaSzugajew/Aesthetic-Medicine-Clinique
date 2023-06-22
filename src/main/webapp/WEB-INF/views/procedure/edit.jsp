<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Edit procedure</title>
    <%--  <link rel="stylesheet" href="<c:url value='/static/css/style.css'/>">--%>
</head>
<body>

<h3>Edit procedure</h3>

<form:form method="post" action="edited" modelAttribute="procedure">

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
    <form:hidden path="id"/>
    <input type="submit" value="Update procedure">
</form:form>

</body>
</html>