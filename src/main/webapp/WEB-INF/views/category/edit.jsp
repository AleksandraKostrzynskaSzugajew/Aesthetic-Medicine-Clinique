<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Edit category</title>
<%--  <link rel="stylesheet" href="<c:url value='/static/css/style.css'/>">--%>
</head>
<body>

<h3>Edit category</h3>

<form:form method="post" action="edited" modelAttribute="category">

    <div>Category name: <form:input path="name"/></div>
    <form:hidden path="id"/>
    <input type="submit" value="Update category">

</form:form>

</body>
</html>
