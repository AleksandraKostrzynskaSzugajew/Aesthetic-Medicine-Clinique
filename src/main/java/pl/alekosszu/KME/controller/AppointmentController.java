package pl.alekosszu.KME.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.alekosszu.KME.entity.employee.Employee;
import pl.alekosszu.KME.entity.employee.Schedule;
import pl.alekosszu.KME.entity.treatments.Procedure;
import pl.alekosszu.KME.entity.user.Appointment;
import pl.alekosszu.KME.entity.user.User;
import pl.alekosszu.KME.entity.user.Wish;
import pl.alekosszu.KME.mailSender.EmailServiceImpl;
import pl.alekosszu.KME.service.*;


import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

@Controller
@RequestMapping("/user/createappointment")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    private final EmployeeService employeeService;
    private final ProcedureService procedureService;
    private final ScheduleService scheduleService;
    private final UserService userService;

    private final EmailServiceImpl emailServiceImpl;
    private final WishService wishService;


    @GetMapping("/appointments")
    public String showAppointmentForm(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName(); // gets my email
        User user = userService.findByEmail(userEmail);
        System.out.println("================================================");
        System.out.println(user.getId());
        System.out.println("================================================");

        model.addAttribute("userId", user.getId());
        return "user/makeAnAppointment";
    }


    @PostMapping("/apposave")
    //  @ResponseBody
    public String createAppointment(@RequestParam(name = "procedureId") Long procedureId,
                                    @RequestParam(name = "employeeId") Long employeeId,
                                    @RequestParam(name = "scheduleId") Long scheduleId,
                                    @RequestParam(name = "hour")
                                    @DateTimeFormat(pattern = "HH:mm") LocalTime hour,
                                    @RequestParam(name = "userId") Long userId) throws jakarta.mail.MessagingException {

        Employee employee = employeeService.findById(employeeId);
        User user = userService.findById(userId);
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

        // Wysyłanie wiadomości e-mail
        String messageText = "Witaj " + user.getFirstName() + "! "
                + "<br><br>"
                + "Twoja wizyta w SzugajewEsthetic Klinika Medycyny Estetycznej została zarezerwowana. Oto szczegóły wizyty:"
                + "<br>"
                + "<ul>"
                + "<li>Data: " + appointment.getDate() + "</li>"
                + "<li>Godzina rozpoczęcia: " + appointment.getStartTime() + "</li>"
                + "<li>Wybrany zabieg: " + procedure.getName() + "</li>"
                + "<li>Przewidywany czas trwania zabiegu: " + procedure.getDuration() + " min</li>"
                + "<li>Pracownik przeprowadzający zabieg: " + employee.getName() + "</li>"
                + "</ul>"
                + "<br>"
                + "<br><br>"
                + "Do zobaczenia, zespół SzugajewEsthetic.";


        emailServiceImpl.sendMail(user.getEmail(), "Potwierdzenie wizyty w SzugajewEsthetic", messageText);

        return "user/successfullyMadeAnAppointment";

    }


    //endpoint dla pobierania listy zabiegow

    @GetMapping("/getprocedures")
    @ResponseBody
    public List<Procedure> getAllProcedures() {
        List<Procedure> all = procedureService.findAll();

        // Tworzenie wpisu "Choose procedure"
        Procedure chooseProcedure = new Procedure();
        chooseProcedure.setId(0L);
        chooseProcedure.setName("Choose procedure");

        // Dodawanie wpisu "Choose procedure" na początku listy
        all.add(0, chooseProcedure);

        return all;
    }


    //endpoint dla pobierania listy lekarzy zaleznie od wybranego zabiegu
    @GetMapping("/getemployees")
    @ResponseBody
    public Collection<Employee> getEmployeesForProcedure(@RequestParam Long procedureId) {
        Collection<Employee> all = procedureService.findEmployeesPerformingProcedureById(procedureId);

        Employee chooseEmployee = new Employee();
        chooseEmployee.setId(0L);
        chooseEmployee.setFirstName("Choose employee");
        chooseEmployee.setLastName("");

        List<Employee> updatedList = new ArrayList<>();
        updatedList.add(chooseEmployee);
        updatedList.addAll(all);

        return updatedList;
    }


    //endpoint dla pobierania listy dat z grafiku konkretnego lekarza
    @GetMapping("/getdates")
    @ResponseBody
    public List<Schedule> findScheduleDatesByEmployeeId(@RequestParam("employeeId") Long employeeId) {
        List<Schedule> schedules = scheduleService.findSchedulesByEmployeeId(employeeId);

        // Tworzenie wpisu "Choose day"
        Schedule chooseDay = new Schedule();
        chooseDay.setId(0L);
        chooseDay.setDate(null);

        // Dodawanie wpisu "Choose day" na początku listy
        List<Schedule> updatedList = new ArrayList<>();
        updatedList.add(chooseDay);
        updatedList.addAll(schedules);

        return updatedList;
    }


    //pobieranie gostepnych godzin z grafiku konkretnego lekarza na konkretny dzien
    @GetMapping("/gah")
    @ResponseBody
    public List<LocalTime> getAvailableHours(Long procedureId, Long employeeId, Long scheduleId) {
        Procedure procedure = procedureService.findById(procedureId);
        Employee employee = employeeService.findById(employeeId);
        Schedule schedule = scheduleService.findById(scheduleId);

        LocalDate date = schedule.getDate();

        LocalTime employeesStartTime = schedule.getStartTime();
        LocalTime employeesEndTime = schedule.getEndTime();
        Duration interval = Duration.ofMinutes(30);

        // Obliczanie czasu trwania procedury
        int durationInMinutes = procedure.getDuration();
        Duration procedureDuration = Duration.ofMinutes(durationInMinutes);

        // Odejmowanie czasu trwania procedury od czasu zakończenia pracy lekarza
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


    @GetMapping("/rmv")
    public String removeAppointmentById(@RequestParam Long employeeId,
                                        @RequestParam Long scheduleId,
                                        @RequestParam Long appointmentId) {

        Employee employee = employeeService.findById(employeeId);
        Schedule schedule = scheduleService.findById(scheduleId);
        Appointment appointment = appointmentService.findById(appointmentId);

        for (Schedule s : employee.getSchedule()) {
            if (s.getScheduledAppointments().contains(appointment)) {
                s.removeFromScheduledAppointments(appointment);
                appointmentService.deleteById(appointmentId);
            }

        }
        return "adminHome";
    }


    @PostMapping("/cancelappointment")
    public String cancelAppointment(@RequestParam Long appointmentId) {
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
        return "user/home";
    }

    @GetMapping("/joinwaitlist")
    public String goToWaitListForm(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Long userId = userService.findByEmail(email).getId();

        model.addAttribute("userId", userId);

        return "user/addToWaitList";
    }

    @PostMapping("/joinwaitlist")
    public String getDataFromAddToWaitListForm(@RequestParam(name = "procedureId") Long procedureId,
                                               @RequestParam(name = "employeeId") Long employeeId,
                                               @RequestParam(name = "scheduleId") Long scheduleId,
                                               @RequestParam(name = "hour") @DateTimeFormat(pattern = "HH:mm") LocalTime hour,
                                               @RequestParam(name = "userId") Long userId) {
        Schedule schedule = scheduleService.findById(scheduleId);

        Wish wish = new Wish();
        wish.setAppointmentDay(schedule.getDate());
        wish.setUser(userService.findById(userId));
        wish.setProcedureId(procedureId);
        wish.setEmployeeId(employeeId);
        wish.setAppointmentTime(hour);
        wishService.save(wish);

        return "user/addToWaitList";
    }

    @GetMapping("/takenhours")
    @ResponseBody
    public Collection<LocalTime> getTakenHours(@RequestParam Long scheduleId) {
        return appointmentService.getReservedHours(scheduleId);
    }


}

