<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin home page</title>


</head>
<body>


<h3>What would you like to do?</h3>


<ul>
    <li>
        <a href="<c:url value='/admin/category/findall'/>">List all categories</a>
        <br>
        <br>
    </li>
    <li>
        <a href="<c:url value='/admin/specialty/findall'/>">List all specialties</a>
        <br>
        <br>

    </li>
    <li>
        <a href="<c:url value='/admin/procedure/findall'/>">List all procedures</a>
        <br>
        <br>

    </li>
    <li>
        <a href="<c:url value='/admin/emp/findall'/>">List all employees</a>
        <br>
        <br>
        <br>
        <br>

    </li>
    <li>
        <a href="<c:url value='/forall/logout'/>">Log out</a>


    </li>


</ul>


</body>
</html>
