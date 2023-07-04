<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Save new employee</title>
    <%--  <link rel="stylesheet" href="<c:url value='/static/css/style.css'/>">--%>


    <script>
        document.addEventListener("DOMContentLoaded", function() {
            var firstNameField = document.getElementById("firstName");
            var lastNameField = document.getElementById("lastName");
            var nameField = document.getElementById("nameField");

            // Nasłuchuj zmian w polach firstName i lastName
            firstNameField.addEventListener("input", updateNameField);
            lastNameField.addEventListener("input", updateNameField);

            // Funkcja aktualizująca pole name
            function updateNameField() {
                var firstName = firstNameField.value;
                var lastName = lastNameField.value;
                var fullName = firstName + " " + lastName;

                nameField.value = fullName;
            }
        });
    </script>

</head>
<body>

<h3>Save new employee</h3>

<form:form method="post" action="saved" modelAttribute="employee">

    <div>First name: <form:input path="firstName" id="firstName"/></div>
    <div>Last name: <form:input path="lastName" id="lastName"/></div>
    <div>Street: <form:input path="street"/></div>
    <div>House number: <form:input path="houseNumber"/></div>
    <div>Post code: <form:input path="postcode"/></div>
    <div>City: <form:input path="city"/></div>

    <div>Specialties: <form:select path="specialties">
        <form:options items="${specialties}" itemValue="id" itemLabel="name"></form:options>
    </form:select></div>


    <div>Main phone number: <form:input path="phoneNumber1"/></div>
    <div>Emergency phone number: <form:input path="phoneNumber2"/></div>

    <div>Performed procedures: <form:select path="performedProcedures">
        <form:options items="${procedures}" itemValue="id" itemLabel="name"></form:options>
    </form:select></div>
    <br>

    <div style="display: none;">Name to be displayed: <form:input path="name" id="nameField"/></div>
    <br>


    <input type="submit" value="Add new employee">
</form:form>

</body>
</html>
