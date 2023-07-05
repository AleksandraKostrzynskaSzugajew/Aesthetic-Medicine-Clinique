<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <%--    datepicker--%>
    <title>Update schedule item</title>
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

    <%--    timepicker--%>
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-timepicker/1.13.18/jquery.timepicker.min.js"></script>
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/jquery-timepicker/1.13.18/jquery.timepicker.min.css">

    <script>
        document.addEventListener("DOMContentLoaded", function () {
            var datepickerField = document.getElementById("datepicker");
            var nameField = document.getElementById("name");

            datepickerField.addEventListener("input", updateNameField);

            // Funkcja aktualizująca pole name
            function updateNameField() {
                var date = datepickerField.value;
                nameField.value = date;
            }
        });

        $(function () {
            $("#datepicker").datepicker({dateFormat: 'yy-mm-dd'});
        });

    </script>


</head>
<body>

<%--<script>--%>
<%--    $(function () {--%>
<%--        $("#datepicker").datepicker();--%>
<%--    });--%>
<%--</script>--%>

<%--<script>--%>
<%--    $(function () {--%>
<%--            // Configuration for timepicker1--%>
<%--            $("#timepicker1").timepicker({--%>
<%--                // Additional options for timepicker1--%>
<%--                interval: 15, // Set time interval to 15 minutes--%>
<%--                minTime: '09:00', // Set minimum selectable time--%>
<%--                maxTime: '16:00', // Set maximum selectable time--%>
<%--                dynamic: false, // Enable dynamic time scrolling--%>
<%--                dropdown: true, // Show time dropdown instead of spinner--%>
<%--                 timeFormat: 'H:M:s'--%>
<%--                // ...--%>
<%--            });--%>

<%--            // Configuration for timepicker2--%>
<%--            $("#timepicker2").timepicker({--%>
<%--            // Additional options for timepicker2--%>
<%--                interval: 15, // Set time interval to 15 minutes--%>
<%--                minTime: '10:00', // Set minimum selectable time--%>
<%--                maxTime: '18:00', // Set maximum selectable time--%>
<%--                dynamic: false, // Enable dynamic time scrolling--%>
<%--                dropdown: true, // Show time dropdown instead of spinner--%>
<%--                timeFormat: 'H:M:s'--%>
<%--            // ...--%>
<%--        });--%>
<%--        });--%>
<%--</script>--%>


<h3>Update schedule item</h3>

<form:form method="post" action="saved/${empId}" modelAttribute="schedule">

    <div>Date: (yyyy-mm-dd) <input type="text" id="datepicker" name="date" value="${schedule.date}" /></div>
    <div>Start time: (hh:mm:ss) <input type="text" id="timepicker1" name="startTime" value="${schedule.startTime}" /></div>
    <div>End time: (hh:mm:ss) <input type="text" id="timepicker2" name="endTime" value="${schedule.endTime}" /></div>
    <div style="display: none">Date to be displayed: <form:input  path="name"  id="nameField" name="name" value="${schedule.name}" /></div>

    <div style="display: none;">Name to be displayed: <form:input path="name" id="nameField"/></div>
    <input type="hidden" name="id" value="${schedule.id}" />
    <input type="submit" value="Update schedule item">

</form:form>


</body>
</html>
