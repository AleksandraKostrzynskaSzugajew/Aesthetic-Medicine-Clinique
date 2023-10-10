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
import pl.alekosszu.KME.entity.user.AppointmentHistory;
import pl.alekosszu.KME.entity.user.User;
import pl.alekosszu.KME.entity.user.Wish;
import pl.alekosszu.KME.repository.AppointmentRepository;
import pl.alekosszu.KME.repository.CategoryRepository;
import pl.alekosszu.KME.repository.ScheduleRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final ScheduleService scheduleService;
    private final WishService wishService;
    private final AppointmentHistoryService appointmentHistoryService;
    private final EmployeeService employeeService;
    private final UserService userService;
    private final ProcedureService procedureService;
    private final AppointmentService appointmentService;


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

        AppointmentHistory appointmentHistory = appointmentHistoryService.findByAppointmentId(id);

        if (appointmentHistory != null) {
            appointmentHistory.setStatus("canceled");
            appointmentHistoryService.save(appointmentHistory);
        }

        Appointment appointment = findById(id);
        Collection<Wish> wishes = wishService.findIfSimilarRequestExists(appointment);

        for (Wish w : wishes) {
            wishService.sendAnEmailAboutTermAvailability(w);
            User user = w.getUser();
            user.removeFromWishList(w);
            wishService.deleteById(w.getId());
        }
        appointmentRepository.deleteById(id);
    }


    public List<LocalTime> getReservedHours(Long scheduleId) {
        Schedule schedule = scheduleService.findById(scheduleId);
        List<Appointment> appointmentsForRequestedDay = schedule.getScheduledAppointments();
        List<LocalTime> takenHours = new ArrayList<>();

        for (Appointment appointment : appointmentsForRequestedDay) {
            LocalTime startTime = appointment.getStartTime();
            LocalTime endTime = appointment.getEndTime();

            LocalTime currentTime = startTime;
            while (currentTime.isBefore(endTime)) {
                takenHours.add(currentTime);
                currentTime = currentTime.plusMinutes(30);
            }
        }
        return takenHours;
    }

    public void addToHistory(Appointment appointment) {
        AppointmentHistory appointmentHistory = new AppointmentHistory();
        appointmentHistory.setAppointmentId(appointment.getId());
        appointmentHistory.setDate(appointment.getDate());
        appointmentHistory.setEmployeeId(appointment.getEmployeeId());
        appointmentHistory.setProcedureId(appointment.getProcedureId());
        appointmentHistory.setEndTime(appointment.getEndTime());
        appointmentHistory.setStartTime(appointment.getStartTime());
        appointmentHistory.setScheduleId(appointment.getScheduleId());
        appointmentHistory.setUserId(appointment.getUserId());
        appointmentHistory.setStatus(appointment.getStatus());
        appointmentHistoryService.save(appointmentHistory);
    }

    public Appointment setAppointmentParamsAndAddToSchedule(Long procedureId, Long employeeId, Long scheduleId, LocalTime hour, Long userId) {
        Employee employee = employeeService.findById(employeeId);
        Appointment appointment = new Appointment();
        appointment.setProcedureId(procedureId);
        appointment.setEmployeeId(employeeId);
        Schedule schedule = scheduleService.findById(scheduleId);
        appointment.setDate(schedule.getDate());
        appointment.setStartTime(hour);
        Procedure procedure = procedureService.findById(procedureId);
        appointment.setEndTime(hour.plusMinutes(procedure.getDuration()));
        appointment.setUserId(userId);
        appointment.setScheduleId(scheduleId);

        appointment.setStatus("planned");
        appointmentService.save(appointment);
        appointmentService.addToHistory(appointment);

        employeeService.addAppointmentToSchedule(appointment);
        return appointment;
    }

    public void cancelAppointment(Long appointmentId) {
        Appointment appointment = appointmentService.findById(appointmentId);
        Long loggedUserId = userService.getCurrentUser();


        Long employeeId = appointment.getEmployeeId();
        Employee employee = employeeService.findById(employeeId);

        if (appointment.getUserId() == loggedUserId) {
            for (Schedule s : employee.getSchedule()) {
                if (s.getScheduledAppointments().contains(appointment)) {
                    s.removeFromScheduledAppointments(appointment);
                    appointmentService.deleteById(appointmentId);
                    if (appointment.getStatus().equals("planned")) {
                        appointment.setStatus("canceled");
                        appointmentService.save(appointment);
                        Collection<Wish> wishes = wishService.findIfSimilarRequestExists(appointment);
                        for (Wish w : wishes) {
                            wishService.sendAnEmailAboutTermAvailability(w);
                        }
                    }
                }

            }
        }
    }

    public void removeAppointment(Long employeeId,Long scheduleId, Long appointmentId){
        Employee employee = employeeService.findById(employeeId);
        Schedule schedule = scheduleService.findById(scheduleId);
        Appointment appointment = appointmentService.findById(appointmentId);

        for (Schedule s : employee.getSchedule()) {
            if (s.getScheduledAppointments().contains(appointment)) {
                s.removeFromScheduledAppointments(appointment);
                appointmentService.deleteById(appointmentId);
                if (appointment.getStatus().equals("planned")) {
                    appointment.setStatus("canceled");
                    appointmentService.save(appointment);
                    Collection<Wish> wishes = wishService.findIfSimilarRequestExists(appointment);
                    for (Wish w : wishes) {
                        wishService.sendAnEmailAboutTermAvailability(w);
                    }
                }
            }

        }
    }
}
