<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Save specialty</title>
<%--  <link rel="stylesheet" href="<c:url value='/static/css/style.css'/>">--%>
</head>
<body>

<h3>Save specialty</h3>

<form:form method="post" action="saved" modelAttribute="specialty">

    <div>Specialty name: <form:input path="name"/></div>
    <input type="submit" value="Add new specialty">
</form:form>

</body>
</html>
