<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
  <title>Save role</title>
  <%--  <link rel="stylesheet" href="<c:url value='/static/css/style.css'/>">--%>
</head>
<body>

<h3>Save role</h3>

<form:form method="post" action="saved" modelAttribute="role">

  <div>Role name: <form:input path="name"/></div>
  <input type="submit" value="Add new role">
</form:form>


<td><a href="<c:url value='/admin/role/save'/>">Add new role</a></td>
<td><a href="<c:url value='/admin/role/remove?id=${role.id}'/>" onclick="return confirm('Are you sure?')">Remove</a></td>
</body>
</html>