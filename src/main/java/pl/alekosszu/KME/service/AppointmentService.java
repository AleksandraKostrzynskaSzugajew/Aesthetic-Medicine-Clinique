package pl.alekosszu.KME.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.alekosszu.KME.entity.treatments.Category;
import pl.alekosszu.KME.entity.user.Appointment;
import pl.alekosszu.KME.repository.AppointmentRepository;
import pl.alekosszu.KME.repository.CategoryRepository;
import pl.alekosszu.KME.repository.ScheduleRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final ScheduleRepository scheduleRepository;


    public void save(Appointment appointment) {
        appointmentRepository.save(appointment);
    }

    public Appointment findById(Long id) {
        return appointmentRepository.findById(id).get();
    }

    public List<Appointment> findAll() {
        return appointmentRepository.findAll();
    }

    public void update(Appointment appointment) {
        appointmentRepository.save(appointment);
    }

    public void deleteById(Long id) {
        appointmentRepository.deleteById(id);
    }


    public boolean isEnoughTimeAvailable(Long employeeId, LocalDate date, LocalTime startTime, int duration) {
        List<LocalTime> occupiedTimes = scheduleRepository.findOccupiedTimes(employeeId, date);
        LocalTime endTime = startTime.plusMinutes(duration);

        for (LocalTime occupiedTime : occupiedTimes) {
            LocalTime occupiedEndTime = occupiedTime.plusMinutes(duration);
            if (startTime.isBefore(occupiedEndTime) && endTime.isAfter(occupiedTime)) {
                return false; // Zajęte godziny się pokrywają
            }
        }

        return true; // jest czas, mozna zapisywac
    }


}
