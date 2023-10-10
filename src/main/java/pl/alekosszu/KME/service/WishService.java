package pl.alekosszu.KME.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.alekosszu.KME.entity.employee.Employee;
import pl.alekosszu.KME.entity.employee.Schedule;
import pl.alekosszu.KME.entity.treatments.Procedure;
import pl.alekosszu.KME.entity.user.Appointment;
import pl.alekosszu.KME.entity.user.User;
import pl.alekosszu.KME.entity.user.Wish;
import pl.alekosszu.KME.mailSender.EmailServiceImpl;
import pl.alekosszu.KME.repository.WishRepository;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class WishService {


    private final WishRepository wishRepository;
    private final ProcedureService procedureService;
    private final EmailServiceImpl emailServiceImpl;
    private final UserService userService;
    private final ScheduleService scheduleService;
    private final AppointmentService appointmentService;
    private final WishService wishService;
    private final EmployeeService employeeService;


    public void save(Wish wish) {
        wishRepository.save(wish);
    }

    public Wish findById(Long id) {
        return wishRepository.findById(id).get();
    }

    public List<Wish> findAll() {
        return wishRepository.findAll();
    }

    public void update(Wish wish) {
        wishRepository.save(wish);
    }

    public void deleteById(Long id) {
        wishRepository.deleteById(id);
    }


    public Collection<Wish> findIfSimilarRequestExists(Appointment appointmentToBeDeleted) {
        Collection<Wish> allClientsWishes = findAll();
        Collection<Wish> matchingWishes = new ArrayList<>();

        Duration durationOfProcedureFromAppointment = Duration.between(appointmentToBeDeleted.getStartTime(), appointmentToBeDeleted.getEndTime());

        for (Wish w : allClientsWishes) {
            Procedure procedureFromWish = procedureService.findById(w.getProcedureId());
            Duration durationOfTheProcedureFromWish = Duration.ofMinutes(procedureFromWish.getDuration());

            if (appointmentToBeDeleted.getEmployeeId().equals(w.getEmployeeId())
                    && appointmentToBeDeleted.getDate().equals(w.getAppointmentDay())) {

                List<LocalTime> appointmentTimeRange = new ArrayList<>();
                LocalTime startTime = appointmentToBeDeleted.getStartTime();
                while (startTime.isBefore(appointmentToBeDeleted.getEndTime())) {
                    appointmentTimeRange.add(startTime);
                    startTime = startTime.plusMinutes(30);
                }

                for (LocalTime appointmentTime : appointmentTimeRange) {
                    if (appointmentTime.equals(w.getAppointmentTime())) {
                        matchingWishes.add(w);
                        break;
                    }
                }
                if (durationOfProcedureFromAppointment.equals(durationOfTheProcedureFromWish)
                        || durationOfTheProcedureFromWish.compareTo(durationOfProcedureFromAppointment) <= 0) {
                    matchingWishes.add(w);
                }
            }
        }

        return matchingWishes;
    }


    @Async
    public void sendAnEmailAboutTermAvailability(Wish wish) {
        emailServiceImpl.sendAnEmailAboutTermAvailability(wish);
    }

    public boolean saveWish(Long procedureId, Long employeeId, Long scheduleId, LocalTime hour, Long userId) {
        Schedule schedule = scheduleService.findById(scheduleId);
        Wish wish = new Wish();
        wish.setAppointmentDay(schedule.getDate());
        wish.setUser(userService.findById(userId));
        wish.setProcedureId(procedureId);
        wish.setEmployeeId(employeeId);
        wish.setAppointmentTime(hour);
        wishRepository.save(wish);
        return true;

    }




}
