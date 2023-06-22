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
    </tr>
    <c:forEach items="${specialties}" var="specialty">
        <tr>
            <td>${specialty.id}</td>
            <td>${specialty.name}</td>

            <td><a href="<c:url value='/admin/specialty/edit?id=${specialty.id}'/>">Edit</a></td>
            <td><a href="<c:url value='/admin/specialty/remove?id=${specialty.id}'/>" onclick="return confirm('Note that it may leave some of your employees without a specialty. Are you sure?')">Remove</a></td>
            <td><a href="<c:url value='/admin/specialty/save' />">Add new specialty</a></td>
        </tr>
    </c:forEach>
</table>

<%--<a href="<c:url value='/author/add'/>">Add new author</a>--%>
<%--<a href="<c:url value='/author/search'/>">Search author</a>--%>
<%--<a href="<c:url value='/author/list'/>">List of all authors</a>--%>
</body>
</html>