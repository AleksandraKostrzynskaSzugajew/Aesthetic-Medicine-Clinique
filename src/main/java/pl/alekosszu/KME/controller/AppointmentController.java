package pl.alekosszu.KME.controller;

import lombok.RequiredArgsConstructor;
import net.bytebuddy.asm.Advice;
import org.springframework.format.annotation.DateTimeFormat;
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
import pl.alekosszu.KME.service.*;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("/user/createappointment")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    private final EmployeeService employeeService;
    private final ProcedureService procedureService;
    private final ScheduleService scheduleService;
    private final UserService userService;

    //  private final MailService mailService;

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
                                    @RequestParam(name = "userId") Long userId) {

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

        appointmentService.save(appointment);

        employeeService.addAppointmentToSchedule(appointment);

        sendConfirmationEmail(appointment);

        return "redirect:appointments";


    }

    private void sendConfirmationEmail(Appointment appointment) {
        // Dane konfiguracyjne serwera SMTP
        String host = "smtp.example.com";
        int port = 587;
        String username = "your_username";
        String password = "your_password";

        // Tworzenie właściwości dla sesji e-mail
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        // Tworzenie autentykacji
        Authenticator auth = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        };

        // Tworzenie sesji e-mail
        Session session = Session.getInstance(props, auth);

        try {
            // Tworzenie wiadomości e-mail
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(appointment.getUserEmail()));
            message.setSubject("Potwierdzenie wizyty");
            message.setText("Potwierdzenie rezerwacji wizyty:\n\n" +
                    "Data: " + appointment.getDate() + "\n" +
                    "Godzina: " + appointment.getStartTime() + "\n" +
                    "Procedura: " + appointment.getProcedureName() + "\n" +
                    "Pracownik: " + appointment.getEmployeeName());

            // Wysłanie wiadomości e-mail
            Transport.send(message);

            System.out.println("Wiadomość e-mail została wysłana z potwierdzeniem wizyty.");
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("Wystąpił błąd podczas wysyłania wiadomości e-mail.");
        }
    }




    //endpoint dla pobierania listy zabiegow
    @GetMapping("/getprocedures")
    @ResponseBody
    public List<Procedure> getAllProcedures() {
        List<Procedure> all =
                procedureService.findAll();
        return all;
    }

    //endpoint dla pobierania listy lekarzy zaleznie od wybranego zabiegu
    @GetMapping("/getemployees")
    @ResponseBody
    // public Collection<Employee> getEmployeesForProcedure(@RequestParam Long procedureId) {
    public Collection<Employee> getEmployeesForProcedure(@RequestParam Long procedureId) {
        Collection<Employee> all =
                procedureService.findEmployeesPerformingProcedureById(procedureId);

        return all;
    }


    //endpoint dla pobierania listy dat z grafiku konkretnego lekarza
    @GetMapping("/getdates")
    @ResponseBody
    public List<Schedule> findScheduleDatesByEmployeeId(@RequestParam("employeeId") Long employeeId) {

        List<Schedule> schedules = scheduleService.findSchedulesByEmployeeId(employeeId);

        List<LocalDate> dates = new ArrayList<>();
        for (Schedule s : schedules) {
            dates.add(s.getDate());
        }

        System.out.println("==============================================");
        System.out.println(dates.toString());

        return schedules;
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


}

