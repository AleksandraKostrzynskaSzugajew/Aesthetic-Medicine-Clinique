package pl.alekosszu.KME.controller;

import lombok.RequiredArgsConstructor;
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
                                    @DateTimeFormat(pattern = "HH:mm") LocalTime hour)
    {

        Appointment appointment = new Appointment();
        appointment.setProcedureId(procedureId);
        appointment.setEmployeeId(employeeId);
        Schedule schedule = scheduleService.findById(scheduleId);
        appointment.setDate(schedule.getDate());
        appointment.setStartTime(hour);
        Procedure procedure = procedureService.findById(procedureId);
        appointment.setEndTime(hour.plusMinutes(procedure.getDuration()));

        appointmentService.save(appointment);

        employeeService.addAppointmentToSchedule(appointment);


        //email part

        //  mailService.sendMail();

        return "redirect:appointments";


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

        // Pobierz grafik dla lekarza
        Schedule schedule = scheduleService.findById(scheduleId);

        // Pobierz zajęte godziny dla danego dnia
        LocalDate date = schedule.getDate();
        List<LocalTime> occupiedTimes = scheduleService.findOccupiedTimes(employeeId, date);

        // Określ minimalną i maksymalną godzinę pracy lekarza
        LocalTime minTime = schedule.getStartTime();
        LocalTime maxTime = schedule.getEndTime();

        // Określ interwał czasowy (np. 30 minut)
        Duration interval = Duration.ofMinutes(30);

        // Lista dostępnych godzin
        List<LocalTime> availableHours = new ArrayList<>();

        // Sprawdź każdą godzinę w przedziale od minTime do maxTime
        LocalTime timeOptionToAdd = minTime;
        while (timeOptionToAdd.isBefore(maxTime)) {
            if (!occupiedTimes.contains(timeOptionToAdd) && appointmentService.isEnoughTimeAvailable(employeeId, schedule.getId(), procedureId)
                    && (schedule.getEndTime().isAfter(timeOptionToAdd.plusMinutes(procedure.getDuration())) || schedule.getEndTime().equals(timeOptionToAdd.plusMinutes(procedure.getDuration())))) {
                availableHours.add(timeOptionToAdd);
            }
            timeOptionToAdd = timeOptionToAdd.plus(interval);
        }
        System.out.println("===========================================================================");
        System.out.println(availableHours.toString());
        System.out.println("===========================================================================");

        return availableHours;

    }


}

