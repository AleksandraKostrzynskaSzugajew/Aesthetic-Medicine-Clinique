package pl.alekosszu.KME.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.alekosszu.KME.entity.employee.Employee;
import pl.alekosszu.KME.entity.employee.Schedule;
import pl.alekosszu.KME.entity.treatments.Category;
import pl.alekosszu.KME.entity.user.Appointment;
import pl.alekosszu.KME.repository.ScheduleRepository;
import pl.alekosszu.KME.service.CategoryService;
import pl.alekosszu.KME.service.EmployeeService;
import pl.alekosszu.KME.service.ScheduleService;

import java.time.LocalTime;
import java.util.List;

@Controller
@RequestMapping("/emp/schedule")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;
    private final EmployeeService employeeService;

    @GetMapping("/save")
    public String saveScheduleForm(Model model, @RequestParam Long id) {
        model.addAttribute("schedule", new Schedule());
        model.addAttribute("empId", id);
        return "schedule/save";
    }

    @PostMapping("/saved/{empId}")
    public String addScheduleItemToEmployee(Schedule schedule, Model model, @PathVariable Long empId) {

        schedule.setEmployeeId(empId);
        Employee employee = employeeService.findById(empId);


        String startString = String.valueOf(schedule.getStartTime());
        String endString = String.valueOf(schedule.getEndTime());
        LocalTime start = LocalTime.parse(startString);
        LocalTime end = LocalTime.parse(endString);
        schedule.setStartTime(start);
        schedule.setEndTime(end);
        scheduleService.save(schedule);


        boolean isDateAlreadyExists = false;

        for (Schedule existingScheduleItem : employee.getSchedule()) {
            if (existingScheduleItem.getDate().equals(schedule.getDate())) {
                isDateAlreadyExists = true;
                break;
            }
        }

        if (!isDateAlreadyExists) {
            employee.addToSchedule(schedule);
            System.out.println("Item for employee with id: " + empId + " saved");
            List<Employee> employees = employeeService.findAll();
            model.addAttribute("employees", employees);

            return "employee/list";
        }
        return "errorPage";


    }

    @GetMapping("/findall")
    public String findAll(Model model) {
        final List<Schedule> scheduleItems = scheduleService.findAll();
        model.addAttribute("scheduleItems", scheduleItems);
        return "schedule/list";
    }

    @GetMapping("/remove")
    public String remove(@RequestParam Long id) {
        scheduleService.deleteById(id);

        return "redirect:findall";
    }

    @GetMapping("/edit")
    public String showEditForm(Model model, @RequestParam Long id) {
        Schedule schedule = scheduleService.findById(id);
        model.addAttribute("schedule", schedule);
        return "schedule/edit";

    }

    @PostMapping("/edited")
    public String finalizeEdit(Schedule schedule, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "redirect:findall";
        }

        scheduleService.update(schedule);
        return "redirect:findall";
    }

    @GetMapping("/rmv")
    public String removeAppointmentById(@RequestParam Long employeeId,
                                        @RequestParam Long scheduleId){

        Employee employee = employeeService.findById(employeeId);
        Schedule schedule = scheduleService.findById(scheduleId);


        for (Schedule s: employee.getSchedule()) {
            if (s.getId()==scheduleId){
                employee.removeScheduleItem(schedule);
                scheduleService.deleteById(scheduleId);
            }

        }
        return "adminHome";
    }


}
