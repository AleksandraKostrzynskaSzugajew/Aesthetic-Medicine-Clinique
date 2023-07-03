package pl.alekosszu.KME.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.alekosszu.KME.entity.employee.Employee;
import pl.alekosszu.KME.entity.employee.Schedule;
import pl.alekosszu.KME.entity.treatments.Procedure;
import pl.alekosszu.KME.entity.user.Appointment;
import pl.alekosszu.KME.service.AppointmentService;
import pl.alekosszu.KME.service.EmployeeService;
import pl.alekosszu.KME.service.ProcedureService;
import pl.alekosszu.KME.service.ScheduleService;

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

    @GetMapping("/appointments")
    public String showAppointmentForm(Model model) {
        // 1. Pobierze listę dostępnych zabiegów
        List<Appointment> availableProcedures = appointmentService.findAll();
        model.addAttribute("availableProcedures", availableProcedures);
        return "user/makeAnAppointment";
    }


    @PostMapping("/appointments")
    @ResponseBody
    public String createAppointment(@RequestParam("procedureId") Long procedureId,
                                    @RequestParam("employeeId") Long employeeId,
                                    @RequestParam("date") LocalDate date,
                                    @RequestParam("startTime") LocalTime startTime) {

        Appointment appointment = new Appointment();
        appointment.setProcedureId(procedureId);
        appointment.setEmployeeId(employeeId);
        appointment.setDate(date);
        appointment.setStartTime(startTime);


        appointmentService.save(appointment);

        if (employeeService.addAppointmentToSchedule(appointment)) {
            return "user/appDemo";
        } else {
            appointmentService.deleteById(appointment.getId());
            return "redirect:appointments";
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
    //public String availableHours(@RequestParam Long procedureId, @RequestParam Long employeeId, @RequestParam LocalDate date) {
    public List<LocalTime> availableHours(Long procedureId, Long employeeId, Long scheduleId) {



        // LocalDate desiredDate = LocalDate.now(); // Określam dzień na ktory chce umowic

        Schedule scheduleToLookForHours = scheduleService.findById(scheduleId);
        LocalDate chosenDate= scheduleToLookForHours.getDate();

        Procedure procedureToPlan = procedureService.findById(procedureId);
        Employee employeeToSearchForTime = employeeService.findById(employeeId);
       // Określ czas trwania zabiegu
        int durationAsInt = procedureToPlan.getDuration();

        LocalTime minTime = employeeService.FindScheduleByDate(employeeId, chosenDate).getStartTime(); // Określ minimalną godzinę pracy lekarza
        LocalTime maxTime = employeeService.FindScheduleByDate(employeeId, chosenDate).getEndTime(); // Określ minimalną godzinę pracy lekarza
        Duration interval = Duration.ofMinutes(30); // Określ interwał czasowy (np. 30 minut)

        List<LocalTime> availableTimes = new ArrayList<>();


        // Pobierz zajęte godziny z grafiku na żądany dzień
        List<LocalTime> occupiedTimes = scheduleService.findOccupiedTimes(employeeId, chosenDate);

        LocalTime startTime = LocalTime.MIN;


        Employee emp = employeeService.findById(employeeId);
        LocalTime doctorsEndTime = scheduleToLookForHours.getEndTime();
        LocalTime employeesMaxTimeToStartProcedure = doctorsEndTime.minusMinutes(procedureToPlan.getDuration());

        LocalTime timeOptionToAdd = minTime;
        while (timeOptionToAdd.isBefore(maxTime)) {
            if (!occupiedTimes.equals(timeOptionToAdd)
                    && appointmentService.isEnoughTimeAvailable
                    (employeeId, scheduleId, procedureId)) {
                availableTimes.add(timeOptionToAdd);
            }
            timeOptionToAdd = timeOptionToAdd.plus(interval);
        }

        System.out.println("===========================================================================");
        System.out.println(availableTimes.toString());
        System.out.println("===========================================================================");

        return availableTimes;

    }

}
