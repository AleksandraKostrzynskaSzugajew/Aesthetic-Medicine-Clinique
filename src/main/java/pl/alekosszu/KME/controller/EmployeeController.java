package pl.alekosszu.KME.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.alekosszu.KME.entity.employee.Employee;
import pl.alekosszu.KME.entity.employee.Schedule;
import pl.alekosszu.KME.entity.employee.Specialty;
import pl.alekosszu.KME.entity.treatments.Procedure;
import pl.alekosszu.KME.service.EmployeeService;
import pl.alekosszu.KME.service.ProcedureService;
import pl.alekosszu.KME.service.ScheduleService;
import pl.alekosszu.KME.service.SpecialtyService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin/emp/")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;
    private final SpecialtyService specialtyService;
    private final ProcedureService procedureService;
    private final ScheduleService scheduleService;


    @ModelAttribute("specialties")
    public List<Specialty> specialties() {
        return specialtyService.findAll();
    }

    @ModelAttribute("procedures")
    public List<Procedure> procedures() {
        return procedureService.findAll();
    }

    @ModelAttribute("schedules")
    public List<Schedule> schedules() {
        return scheduleService.findAll();
    }


    @GetMapping("/save")
    public String saveEmployeeForm(Model model) {
        model.addAttribute("employee", new Employee());

        return "employee/save";
    }

    @PostMapping("/saved")
    public String employeeSaved(Employee employee) {
        employeeService.save(employee);
        return "redirect:findall";
    }

    @GetMapping("/findall")
    public String findAll(Model model) {
        final List<Employee> employees = employeeService.findAll();
        model.addAttribute("employees", employees);

        return "employee/list";
    }

    @GetMapping("/remove")
    public String remove(@RequestParam Long id) {
        employeeService.deleteById(id);
        return "redirect:findall";
    }

    @GetMapping("/edit")
    public String showEditForm(Model model, @RequestParam Long employeeId) {
        Employee employee = employeeService.findById(employeeId);
        model.addAttribute("employee", employee);
        return "employee/edit";

    }

    @PostMapping("/edited")
    public String finalizeEdit(Employee employee, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "employee/edit";
        }

        employeeService.update(employee);
        return "redirect:findall";
    }

    @GetMapping("/removeScheduleItem")
    public String removeScheduleItem(@RequestParam Long employeeId,
                                     @RequestParam Long scheduleId) {
        scheduleService.removeAllAppointmentsFromScheduleItem(scheduleId);
        Employee employee = employeeService.findById(employeeId);
        Schedule schedule = scheduleService.findById(scheduleId);
        employee.removeScheduleItem(schedule);
        System.out.println("===================================");
        System.out.println("removed");
        System.out.println("===================================");
        return "employee/list";
    }

}
