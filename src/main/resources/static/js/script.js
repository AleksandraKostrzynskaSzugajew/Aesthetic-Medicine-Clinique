window.addEventListener('DOMContentLoaded', () => {
    // Pobierz elementy formularza
    const reservationForm = document.getElementById('reservationForm');
    const procedureSelect = document.getElementById('procedureId');
    const doctorSelect = document.getElementById('employeeId');
    const daySelect = document.getElementById('scheduleId');
    const hourSelect = document.getElementById('hour');

    function updateSelect(selectElement, options, selectedValue) {
        selectElement.innerHTML = '';

        // Dodaj nowe opcje
        options.forEach(option => {
            const optionElement = document.createElement('option');
            optionElement.value = option.id;
            optionElement.textContent = option.name;

            if (option.id === selectedValue) {
                optionElement.selected = true;
            }

            selectElement.appendChild(optionElement);
        });
    }


    function updateSelectHours(selectElement, hours) {
        selectElement.innerHTML = '';

        hours.forEach((hour) => {
            const optionElement = document.createElement('option');
            optionElement.value = hour;
            optionElement.textContent = hour;
            selectElement.appendChild(optionElement);
        });
    }


    fetch('/user/createappointment/getprocedures')
        .then(response => response.json())
        .then(procedures => {
            updateSelect(procedureSelect, procedures);
        });

    procedureSelect.addEventListener('change', () => {
        const selectedProcedureId = procedureSelect.value;
        fetch(`/user/createappointment/getemployees?procedureId=${selectedProcedureId}`)
            .then(response => response.json())
            .then(doctors => {
                updateSelect(doctorSelect, doctors);
            });
    });

    doctorSelect.addEventListener('change', () => {
        const selectedEmployeeId = doctorSelect.value;
        fetch(`/user/createappointment/getdates?employeeId=${selectedEmployeeId}`)
            .then(response => response.json())
            .then(dates => {
                updateSelect(daySelect, dates);
            });
    });

    daySelect.addEventListener('change', () => {
        const procedureIdx = procedureSelect.value;
        const employeeIdx = doctorSelect.value;
        const scheduleIdx = daySelect.value;

        console.log(procedureIdx);
        fetch(`/user/createappointment/gah?procedureId=${procedureIdx}&employeeId=${employeeIdx}&scheduleId=${scheduleIdx}`)
            .then(response => response.json())
            .then(hours => {
                console.log(hours)
                updateSelectHours(hourSelect, hours, hourSelect.value);
            });
    });


})