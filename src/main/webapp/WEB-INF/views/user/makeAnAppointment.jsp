<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Appointment maker</title>
</head>
<body>Welcome to appointment maker!</h1>

<h1>Formularz rezerwacji wizyty</h1>

For which procedure would you like to make an appointment?

<form id="reservationForm">
    <label for="procedure">Zabieg:</label>
    <select id="procedure" name="procedure">
        <!-- Opcje pobierane dynamicznie za pomocą JavaScript -->
    </select>

    <label for="doctor">Lekarz:</label>
    <select id="doctor" name="doctor">
        <!-- Opcje pobierane dynamicznie za pomocą JavaScript -->
    </select>

    <label for="date">Data i godzina:</label>
    <input type="date" id="date" name="date">

    <button type="submit">Zarezerwuj</button>
</form>

<script src="script.js"></script>
</body>
</html>
