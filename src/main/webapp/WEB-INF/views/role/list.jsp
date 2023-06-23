<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>All roles</title>
    <%--  <link rel="stylesheet" href="<c:url value='/static/css/style.css'/>">--%>
</head>
<body>

<h3>All roles</h3>

<c:forEach items="${roles}" var="item">
    <tr>
        <td>${item.id}</td>
        <td>${item.name}</td>

        <td><a href="<c:url value='/admin/role/remove?id=${item.id}'/>" onclick="return confirm('Are you sure?')">Remove</a></td>
    </tr>
</c:forEach>


<td><a href="<c:url value='/admin/role/save'/>">Add new role</a></td>
</body>
</html>