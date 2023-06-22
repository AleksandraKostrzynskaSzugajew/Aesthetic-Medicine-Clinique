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
    <c:forEach items="${categories}" var="category">
        <tr>
            <td>${category.id}</td>
            <td>${category.name}</td>

            <td><a href="<c:url value='/admin/category/edit?id=${category.id}'/>">Edit</a></td>
            <td><a href="<c:url value='/admin/category/remove?id=${category.id}'/>" onclick="return confirm('Note that it may leave some of your treatments without a category. Are you sure?')">Remove</a></td>
            <td><a href="<c:url value='/admin/category/save' />">Add new category</a></td>
        </tr>
    </c:forEach>
</table>

<%--<a href="<c:url value='/author/add'/>">Add new author</a>--%>
<%--<a href="<c:url value='/author/search'/>">Search author</a>--%>
<%--<a href="<c:url value='/author/list'/>">List of all authors</a>--%>
</body>
</html>