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
        String userEmail = authentication.getName();
        User user = userService.findByEmail(userEmail);
        model.addAttribute("userId", user.getId());
        return "user/makeAnAppointment";
    }


    @PostMapping("/apposave")
    public String createAppointment(@RequestParam(name = "procedureId") Long procedureId,
                                    @RequestParam(name = "employeeId") Long employeeId,
                                    @RequestParam(name = "scheduleId") Long scheduleId,
                                    @RequestParam(name = "hour")
                                    @DateTimeFormat(pattern = "HH:mm") LocalTime hour,
                                    @RequestParam(name = "userId") Long userId) throws jakarta.mail.MessagingException {

        Appointment appointment = appointmentService.setAppointmentParamsAndAddToSchedule(procedureId, employeeId, scheduleId, hour, userId);
        return "user/successfullyMadeAnAppointment";

    }


    @GetMapping("/getprocedures")
    @ResponseBody
    public List<Procedure> getAllProcedures() {
        List<Procedure> all = procedureService.findAll();
        Procedure chooseProcedure = new Procedure();
        chooseProcedure.setId(0L);
        chooseProcedure.setName("Choose procedure");
        all.add(0, chooseProcedure);
        return all;
    }


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

    @GetMapping("/getdates")
    @ResponseBody
    public List<Schedule> findScheduleDatesByEmployeeId(@RequestParam("employeeId") Long employeeId) {
        List<Schedule> schedules = scheduleService.findSchedulesByEmployeeId(employeeId);
        Schedule chooseDay = new Schedule();
        chooseDay.setId(0L);
        chooseDay.setDate(null);
        List<Schedule> updatedList = new ArrayList<>();
        updatedList.add(chooseDay);
        updatedList.addAll(schedules);
        return updatedList;
    }


    @GetMapping("/gah")
    @ResponseBody
    public List<LocalTime> getAvailableHours(Long procedureId, Long employeeId, Long scheduleId) {
        return scheduleService.getAvaliableHours(procedureId, employeeId, scheduleId);
    }


    @GetMapping("/rmv")
    public String removeAppointmentById(@RequestParam Long employeeId,
                                        @RequestParam Long scheduleId,
                                        @RequestParam Long appointmentId) {
        appointmentService.removeAppointment(employeeId, scheduleId, appointmentId);
        return "adminHome";
    }


    @PostMapping("/cancelappointment")
    public String cancelAppointment(@RequestParam Long appointmentId) {
        appointmentService.cancelAppointment(appointmentId);
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
        if (wishService.saveWish(procedureId, employeeId, scheduleId, hour, userId)) {
            return "user/addToWaitList";
        }
        return "errorPage";


    }

    @GetMapping("/takenhours")
    @ResponseBody
    public Collection<LocalTime> getTakenHours(@RequestParam Long scheduleId) {
        return appointmentService.getReservedHours(scheduleId);
    }


}

