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
    <div>End time: (hh:mm:ss) <input type="text" id="timepicker2" value="2" name="endTime">
    </div>
<%--    <form:hidden path="employee.id"/>--%>
    <input type="submit" value="Add new schedule item">
</form:form>

</body>
</html>


<%--another optionals for script--%>
<%--<script>--%>
<%--    $(function() {--%>
<%--        $("#datepicker").datepicker({--%>
<%--            dateFormat: "yy-mm-dd",--%>
<%--            minDate: 0, // Restrict dates in the past--%>
<%--            maxDate: "+1w", // Restrict dates within the next week--%>
<%--            showButtonPanel: true, // Show a button panel for navigation--%>
<%--            changeMonth: true, // Allow month selection--%>
<%--            changeYear: true, // Allow year selection--%>
<%--            yearRange: "2000:2030" // Specify the range of selectable years--%>
<%--        });--%>
<%--    });--%>
<%--</script>--%>


<%--<script>--%>
<%--    $(function() {--%>
<%--        $("#timepicker").timepicker({--%>
<%--            timeFormat: 'hh:mm tt',--%>
<%--            interval: 15, // Set time interval to 15 minutes--%>
<%--            minTime: '09:00', // Set minimum selectable time--%>
<%--            maxTime: '18:00', // Set maximum selectable time--%>
<%--            dynamic: false, // Enable dynamic time scrolling--%>
<%--            dropdown: true // Show time dropdown instead of spinner--%>
<%--        });--%>
<%--    });--%>
<%--</script>--%>