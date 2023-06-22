<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>All categories</title>
</head>
<body>

<table>
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Duration</th>
        <th>Cost</th>
        <th>Category</th>
        <th>Employees entitled to perform</th>
    </tr>
    <c:forEach items="${procedures}" var="procedure">
        <tr>
            <td>${procedure.id}</td>
            <td>${procedure.name}</td>
            <td>${procedure.duration}</td>
            <td>${procedure.cost}</td>
            <td>${procedure.category}</td>
            <td>${procedure.employeesPerformingProcedure}</td>

            <td><a href="<c:url value='/admin/procedure/edit?id=${procedure.id}'/>">Edit</a></td>
            <td><a href="<c:url value='/admin/procedure/remove?id=${procedure.id}'/>" onclick="return confirm('Are you sure?')">Remove</a></td>
            <td><a href="<c:url value='/admin/procedure/save' />">Add new procedure</a></td>
        </tr>
    </c:forEach>
</table>

<%--<a href="<c:url value='/author/add'/>">Add new author</a>--%>
<%--<a href="<c:url value='/author/search'/>">Search author</a>--%>
<%--<a href="<c:url value='/author/list'/>">List of all authors</a>--%>
</body>
</html>