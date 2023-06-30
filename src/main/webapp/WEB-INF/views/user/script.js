window.addEventListener('DOMContentLoaded', () => {
    // Pobierz elementy formularza
    const reservationForm = document.getElementById('reservationForm');
    const procedureSelect = document.getElementById('procedure');
    const doctorSelect = document.getElementById('doctor');

    // Funkcja do pobierania listy zabiegów z backendu
    function fetchProcedures() {
        return fetch('/api/procedures') // Endpoint do pobrania listy zabiegów
            .then(response => response.json());
    }

    // Funkcja do pobierania listy lekarzy na podstawie wybranego zabiegu
    function fetchDoctors(procedureId) {
        return fetch(`/api/doctors?procedureId=${procedureId}`) // Endpoint do pobrania listy lekarzy dla konkretnego zabiegu
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
        const doctorId = doctorSelect.value;
        const date = document.getElementById('date').value;

        // Wyślij dane do backendu
        fetch('/api/reservation', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                procedureId,
                doctorId,
                date
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
