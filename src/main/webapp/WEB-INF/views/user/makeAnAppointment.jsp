<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Appointment maker</title>
</head>
<body>
<h1>Welcome to appointment maker!</h1>

<h1>Formularz rezerwacji wizyty</h1>

For which procedure would you like to make an appointment?

<form id="reservationForm">
    <label for="procedure">Zabieg:</label>
    <select id="procedure" name="procedure">
        <option value="">Wybierz zabieg</option>
    </select>

    <label for="doctor">Lekarz:</label>
    <select id="doctor" name="doctor">
        <option value="">Wybierz osobę przeprowadzającą zabieg</option>
    </select>

    <label for="day">Dzień:</label>
    <select id="day" name="day">
        <option value="">Wybierz dzień</option>
    </select>

    <label for="hour">Godzina:</label>
    <select id="hour" name="hour">
        <option value="">Wybierz godzinę</option>
    </select>

    <button type="submit">Zarezerwuj</button>
</form>

<script src="/js/script.js"></script>
</body>
</html>