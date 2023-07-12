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

            // Iteruj przez godziny od startTime do endTime i dodawaj do listy takenHours
            LocalTime currentTime = startTime;
            while (currentTime.isBefore(endTime)) {
                takenHours.add(currentTime);
                currentTime = currentTime.plusMinutes(30);
            }
        }

        System.out.println("=======================================");
        System.out.println(takenHours);
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

}
