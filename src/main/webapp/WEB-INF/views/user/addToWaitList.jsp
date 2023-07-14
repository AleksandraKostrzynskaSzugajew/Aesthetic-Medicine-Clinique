<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Appointment maker</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.3.0/css/bootstrap.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.3.0/js/bootstrap.min.js"></script>
</head>
<body>
<h3>Add me to waiting list</h3>

<h5>Please, fill the form below so we can notify when needed</h5>

For which procedure would you like to make an appointment?

<form id="reservationForm" action="/user/createappointment/joinwaitlist" method="post">
    <select id="procedureId" name="procedureId">
        <option value="" disabled selected hidden>Choose treatment</option> <!-- Pusta opcja z atrybutem disabled -->
    </select>
    <br>
    <br>
    Choose employee to perform procedure
    <br>
    <select id="employeeId" name="employeeId">
        <option value="" disabled selected hidden>Choose employee</option> <!-- Pusta opcja z atrybutem disabled -->
    </select>
    <br>
    <br>
    Choose the most suitable day for your visit
    <select id="scheduleId" name="scheduleId">
        <option value="" disabled selected hidden>Choose day</option> <!-- Pusta opcja z atrybutem disabled -->
    </select>
    <br>
    <br>
    What time that is currently unavailable would be the best for you?
    <select id="hour" name="hour">
        <option value="" disabled selected hidden>Choose time</option> <!-- Pusta opcja z atrybutem disabled -->
    </select>

    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <input type="hidden" name="userId" value="${userId}"/>
    <button type="submit" id="notifyButton">Notify me</button>

</form>


<a href="<c:url value='/user/home'/>">Home page</a>


<!-- Modal -->
<div class="modal fade" id="successModal" tabindex="-1" aria-labelledby="successModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="successModalLabel">Success!</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                Your request has been successfully submitted. We will notify you when needed.
            </div>
        </div>
    </div>
</div>



<script src="/js/waitingList.js"></script>
</body>
</html>
