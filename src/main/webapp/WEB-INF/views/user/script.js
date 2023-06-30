window.addEventListener('DOMContentLoaded', () => {
    // Pobierz elementy formularza
    const reservationForm = document.getElementById('reservationForm');
    const procedureSelect = document.getElementById('procedure');
    const doctorSelect = document.getElementById('doctor');
    const daySelect = document.getElementById('day');
    const hourSelect = document.getElementById('hour');


    // Funkcja do pobierania listy zabiegów z backendu
    function fetchProcedures() {
        return fetch('/user/createappointment/getprocedures') // Endpoint do pobrania listy zabiegów
            .then(response => response.json());
    }

    // Funkcja do pobierania listy lekarzy na podstawie wybranego zabiegu
    function fetchDoctors(procedureId) {
        return fetch(`/user/createappointment/getemployees?procedureId=${procedureId}`) // Endpoint do pobrania listy lekarzy dla konkretnego zabiegu
            .then(response => response.json());
    }

    // Funkcja do pobierania listy dni pracujacych dla danego pracownika
    function fetchDoctorsWorkingDays(employeeId) {
        return fetch(`/user/createappointment/getdates?employeeId=${employeeId}`) // Endpoint do pobrania listy dat dla konkretnego lekarza
            .then(response => response.json());
    }

    // Funkcja do pobierania listy dostepnych godzin dla wybranego pracownika
    function fetchDoctorsAvailableHours(procedureId, employeeId, date) {
        return fetch(`/user/createappointment/gah?procedureId=${procedureId}&employeeId=${employeeId}&date=${date}`) // Endpoint do pobrania listy dat dla konkretnego lekarza
            .then(response => response.json());
    }


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

    // Obsługa zdarzenia wysłania formularza
    reservationForm.addEventListener('submit', event => {
        event.preventDefault();

        // Pobierz wartości pól formularza
        const procedureId = procedureSelect.value;
        const employeeId = doctorSelect.value;
        const date = daySelect.value;
        const startTime = hourSelect.value;

        // Wyślij dane do backendu
        fetch('/user/createappointment/appointments', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                procedureId,
                empoloyeeId,
                date,
                startTime
            })
        })
            .then(response => {
                if (response.ok) {
                    // Pomyślnie zarezerwowano wizytę
                    alert('Wizyta została zarezerwowana!');
                    reservationForm.reset();
                } else {
                    // Błąd podczas rezerwacji wizyty
                    alert('Wystąpił błąd podczas rerezwacji wizyty')
                }
            })
    })
});
