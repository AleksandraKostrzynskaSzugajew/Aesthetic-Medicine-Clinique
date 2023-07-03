window.addEventListener('DOMContentLoaded', () => {
    // Pobierz elementy formularza
    const reservationForm = document.getElementById('reservationForm');
    const procedureSelect = document.getElementById('procedure');
    const doctorSelect = document.getElementById('doctor');
    const daySelect = document.getElementById('day');
    const hourSelect = document.getElementById('hour');

// Funkcja do aktualizacji listy opcji w polu wyboru
    function updateSelect(selectElement, options) {
        // Wyczyść aktualne opcje
        selectElement.innerHTML = '';

        // Dodaj nowe opcje
        options.forEach(option => {
            const optionElement = document.createElement('option');
            optionElement.value = option.id;
            optionElement.textContent = option.name;
            selectElement.appendChild(optionElement);
        });
    }

    function updateSelectHours(selectElement, hours) {
        selectElement.innerHTML = '';

        hours.forEach((hour, index) => {
            const optionElement = document.createElement('option');
            optionElement.value = index; // Ustawiamy wartość opcji jako indeks w tablicy
            optionElement.textContent = hour; // Wykorzystujemy godzinę jako tekst opcji
            selectElement.appendChild(optionElement);
        });
    }


// Pobierz listę zabiegów z serwera i zaktualizuj pole wyboru "Zabieg"
    fetch('/user/createappointment/getprocedures')
        .then(response => response.json())
        .then(procedures => {
            updateSelect(procedureSelect, procedures);
        });

// Obsłuż zmianę wybranego zabiegu i pobierz listę lekarzy
    procedureSelect.addEventListener('change', () => {
        const selectedProcedureId = procedureSelect.value;
        fetch(`/user/createappointment/getemployees?procedureId=${selectedProcedureId}`)
            .then(response => response.json())
            .then(doctors => {
                updateSelect(doctorSelect, doctors);
            });
    });

// Obsłuż zmianę wybranego lekarza i pobierz listę dat
    doctorSelect.addEventListener('change', () => {
        const selectedEmployeeId = doctorSelect.value;
        fetch(`/user/createappointment/getdates?employeeId=${selectedEmployeeId}`)
            .then(response => response.json())
            .then(dates => {
                updateSelect(daySelect, dates);
            });
    });

// Obsłuż zmianę wybranej daty i pobierz listę dostępnych godzin
    daySelect.addEventListener('change', () => {
        const selectedProcedureId = procedureSelect.value;
        const selectedEmployeeId = doctorSelect.value;
        const scheduleId = daySelect.value;
        fetch(`/user/createappointment/gah?procedureId=${selectedProcedureId}&employeeId=${selectedEmployeeId}&scheduleId=${scheduleId}`)
            .then(response => response.json())
            .then(hours => {
                console.log(hours)
                updateSelectHours(hourSelect, hours);
            });
    });


    // Obsługa zdarzenia wysłania formularza
    // reservationForm.addEventListener('submit', event => {
    //     event.preventDefault();
    //
    //     // Pobierz wartości pól formularza
    //     const procedureId = procedureSelect.value;
    //     const employeeId = doctorSelect.value;
    //     const date = daySelect.value;
    //     const startTime = hourSelect.value;
    //
    //     // Wyślij dane do backendu
    //     fetch(`/user/createappointment/apposave?procedureId=${procedureId}&employeeId=${employeeId}&date=${date}&startTime=${startTime}`, {
    //         method: 'POST',
    //         headers: {
    //             'Content-Type': 'application/json'
    //         },
    //         body: JSON.stringify({
    //             procedureId,
    //             employeeId,
    //             date,
    //             startTime
    //         })
    //     })
    //         .then(response => {
    //             if (response.ok) {
    //                 // Pomyślnie zarezerwowano wizytę
    //                 alert('Wizyta została zarezerwowana!');
    //                 reservationForm.reset();
    //             } else {
    //                 // Błąd podczas rezerwacji wizyty
    //                 alert('Wystąpił błąd podczas rezerwacji wizyty');
    //             }
    //         });
    // });
})
