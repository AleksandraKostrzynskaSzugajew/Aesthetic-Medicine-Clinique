<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <%--    datepicker--%>
    <title>Save schedule item</title>
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
            var nameField = document.getElementById("nameField");

            datepickerField.addEventListener("change", updateNameField);

            // Funkcja aktualizująca pole name
            function updateNameField() {
                var date = datepickerField.value;
                var nameValue = date.substring(0, 10); // Pobierz tylko pierwsze 10 znaków (yyyy-mm-dd)
                nameField.value = nameValue;
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


<h3>Save schedule item</h3>

<form:form method="post" action="saved/${empId}" modelAttribute="schedule">
    <div>Date: (yyyy-mm-dd) <input type="text" id="datepicker" name="date"></div>
    <div>Start time: (hh:mm:ss) <input type="text" id="timepicker1" value="1" name="startTime"></div>
    <div>End time: (hh:mm:ss) <input type="text" id="timepicker2" value="2" name="endTime"></div>
    <div style="display: none;">Name to be displayed: <form:input path="name" id="nameField"/></div>
    <%--<form:hidden path="employee.id"/>--%>
    <input type="submit" value="Add new schedule item">
</form:form>

</body>
</html>

