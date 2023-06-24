package pl.alekosszu.KME.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.alekosszu.KME.entity.treatments.Procedure;
import pl.alekosszu.KME.entity.user.Appointment;
import pl.alekosszu.KME.service.AppointmentService;
import pl.alekosszu.KME.service.EmployeeService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Controller
@RequestMapping("/user/createappointment")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    private final EmployeeService employeeService;

    @GetMapping("/appointments")
    public String showAppointmentForm(Model model) {
        // 1. Pobierze listę dostępnych zabiegów
        List<Appointment> availableProcedures = appointmentService.findAll();
        model.addAttribute("availableProcedures", availableProcedures);
        return "user/makeAnAppointment";
    }


    @PostMapping("/appointments")
    @ResponseBody
    public String createAppointment(@RequestParam("procedure") Long procedureId,
                                    @RequestParam("employee") Long employeeId,
                                    @RequestParam("date") LocalDate date,
                                    @RequestParam("startTime") LocalTime startTime,
                                    @RequestParam("endTime") LocalTime endTime) {

        Appointment appointment = new Appointment();
        appointment.setProcedureId(procedureId);
        appointment.setEmployeeId(employeeId);
        appointment.setDate(date);
        appointment.setStartTime(startTime);
        appointment.setEndTime(endTime);

        appointmentService.save(appointment);

        if (employeeService.addAppointmentToSchedule(appointment)) {
            return "user/appDemo";
        } else {
            appointmentService.deleteById(appointment.getId());
            return "redirect:appointments";
        }

    }
}
