package pl.alekosszu.KME.service;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.alekosszu.KME.entity.employee.Employee;
import pl.alekosszu.KME.entity.employee.Schedule;
import pl.alekosszu.KME.entity.employee.Specialty;
import pl.alekosszu.KME.entity.treatments.Procedure;
import pl.alekosszu.KME.entity.user.Appointment;
import pl.alekosszu.KME.repository.AppointmentRepository;
import pl.alekosszu.KME.repository.ScheduleRepository;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final ProcedureService procedureService;
    private final EmployeeService employeeService;
    private final ScheduleService scheduleService;


    public void save(Schedule schedule) {
        scheduleRepository.save(schedule);
    }

    public Schedule findById(Long id) {
        return scheduleRepository.findById(id).get();
    }

    public List<Schedule> findAll() {
        return scheduleRepository.findAll();
    }

    public void update(Schedule schedule) {
        scheduleRepository.save(schedule);
    }

    public void deleteById(Long id) {
        removeAllAppointmentsFromScheduleItem(id);
        scheduleRepository.deleteById(id);
    }

    public List<Schedule> findByEmployeeId(Long id) {
        return scheduleRepository.findByEmployeeId(id);
    }

    public List<Schedule> findSchedulesByEmployeeId(@Param("employeeId") Long employeeId) {
        return scheduleRepository.findSchedulesByEmployeeId(employeeId);
    }


//    public List<LocalTime> findOccupiedTimes(@Param("employeeId") Long employeeId,
//                                             @Param("scheduleId") Long scheduleId){
//        return scheduleRepository.findOccupiedTimes(employeeId, scheduleId);
//    }


    public void removeAllAppointmentsFromScheduleItem(Long scheduleId) {
        Schedule schedule = findById(scheduleId);
        List<Appointment> appointmentsToCancel = schedule.getScheduledAppointments();
        for (Appointment appointment : appointmentsToCancel) {
            deleteById(appointment.getId());
        }
    }

    public List<Appointment> findAppointmentsByScheduleId(@Param("scheduleId") Long scheduleId){
       return scheduleRepository.findAppointmentsByScheduleId(scheduleId);
    };

    public List<LocalTime> getAvaliableHours(Long procedureId, Long employeeId, Long scheduleId){
        Procedure procedure = procedureService.findById(procedureId);
        Schedule schedule = scheduleService.findById(scheduleId);


        LocalTime employeesStartTime = schedule.getStartTime();
        LocalTime employeesEndTime = schedule.getEndTime();
        Duration interval = Duration.ofMinutes(30);

        int durationInMinutes = procedure.getDuration();
        Duration procedureDuration = Duration.ofMinutes(durationInMinutes);

        LocalTime adjustedEndTime = employeesEndTime.minus(procedureDuration);

        List<LocalTime> allAvailableHours = new ArrayList<>();
        allAvailableHours.add(employeesStartTime);
        LocalTime increasedTime = employeesStartTime;
        boolean isAfterEmployeesWorkingHours = false;
        while (!isAfterEmployeesWorkingHours) {
            increasedTime = increasedTime.plus(interval);

            boolean isDuringAppointment = false;
            for (Appointment app : schedule.getScheduledAppointments()) {
                LocalTime start = app.getStartTime();
                LocalTime end = app.getEndTime();

                if (start.equals(increasedTime) || (increasedTime.isAfter(start) && increasedTime.isBefore(end))) {
                    isDuringAppointment = true;
                    break;
                }
            }

            if (!isDuringAppointment && !increasedTime.isAfter(adjustedEndTime)) {
                allAvailableHours.add(increasedTime);
            }

            if (increasedTime.equals(employeesEndTime)) {
                isAfterEmployeesWorkingHours = true;
            }
        }

        return allAvailableHours;
    }

}
