package pl.alekosszu.KME.service;


import lombok.RequiredArgsConstructor;
import net.bytebuddy.asm.Advice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.alekosszu.KME.entity.employee.Employee;
import pl.alekosszu.KME.entity.employee.Schedule;
import pl.alekosszu.KME.entity.treatments.Category;
import pl.alekosszu.KME.entity.treatments.Procedure;
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
    private final ScheduleService scheduleService;
    private final ProcedureService procedureService;


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


//    public boolean isEnoughTimeAvailable(Long employeeId, Long scheduleId, Long procedureId) {
//        Procedure procedure = procedureService.findById(procedureId);
//        Schedule schedule = scheduleService.findById(scheduleId);
//
//        LocalTime employeesStartTime = schedule.getStartTime();
//        LocalTime employeesEndTime = schedule.getEndTime();
//
//        // Oblicz czas trwania procedury w minutach
//        int procedureDuration = procedure.getDuration();
//
//        // Oblicz planowany czas zakończenia procedury
//        LocalTime procedureEndTime = employeesStartTime.plusMinutes(procedureDuration);
//
//        // Sprawdź, czy czas zakończenia procedury mieści się w ramach czasu pracy lekarza
//        boolean isWithinWorkingHours = procedureEndTime.isBefore(employeesEndTime);
//
//        if (!isWithinWorkingHours) {
//            return false; // Procedura kończy się poza godzinami pracy lekarza
//        }
//
//        // Pobierz zajęte godziny z grafiku na żądany dzień
//        List<LocalTime> occupiedTimes = scheduleService.f(employeeId, scheduleId);
//
//        // Sprawdź, czy istnieje pokrywanie się zajętych godzin
//        for (LocalTime occupiedTime : occupiedTimes) {
//            LocalTime occupiedEndTime = occupiedTime.plusMinutes(procedureDuration);
//
//            // Sprawdź, czy czas rozpoczęcia lub zakończenia wizyty znajduje się w granicach czasu
//            if ((occupiedTime.isAfter(employeesStartTime) && occupiedTime.isBefore(procedureEndTime))
//                    || (occupiedEndTime.isAfter(employeesStartTime) && occupiedEndTime.isBefore(procedureEndTime))) {
//                return false; // Istnieje pokrywanie się wizyt w grafiku
//            }
//        }
//
//        return true; // Nie ma pokrywających się wizyt
//    }





}
