package pl.alekosszu.KME.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.alekosszu.KME.entity.employee.Employee;
import pl.alekosszu.KME.entity.employee.Schedule;
import pl.alekosszu.KME.entity.treatments.Category;
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
    public String saveScheduleForm(Model model) {
        model.addAttribute("schedule", new Schedule());
        return "schedule/save";
    }

    @PostMapping("/saved")
    public String scheduleItemSaved(Schedule schedule, Model model) {
        String startString = String.valueOf(schedule.getStartTime());
        String endString = String.valueOf(schedule.getEndTime());
        LocalTime start = LocalTime.parse(startString);
        LocalTime end = LocalTime.parse(endString);
        schedule.setStartTime(start);
        schedule.setEndTime(end);
        scheduleService.save(schedule);
        System.out.println("Schedule saved");
         List<Employee> employees = employeeService.findAll();
        model.addAttribute("employees", employees);
        return "employee/list";
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
            return "schedule/edit";
        }

        scheduleService.update(schedule);
        return "redirect:findall";
    }


}
