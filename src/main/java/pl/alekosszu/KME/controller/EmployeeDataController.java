package pl.alekosszu.KME.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.alekosszu.KME.entity.employee.EmployeeData;
import pl.alekosszu.KME.entity.employee.Specialty;
import pl.alekosszu.KME.service.EmployeeDataService;
import pl.alekosszu.KME.service.SpecialtyService;

import java.util.List;

@Controller
@RequestMapping("/emp/data")
@RequiredArgsConstructor
public class EmployeeDataController {

    private final EmployeeDataService employeeDataService;

    @GetMapping("/save")
    public String saveDataForm(Model model) {
        model.addAttribute("empData", new EmployeeData());
        return "empData/save";
    }

    @PostMapping("/saved")
    public String empDataSaved(EmployeeData employeeData) {
        employeeDataService.save(employeeData);
        return "done";
    }

//
//    @GetMapping("/remove")
//    public String remove(@RequestParam Long id) {
//        employeeDataService.deleteById(id);
//        return "redirect:findall";
//    }

    @GetMapping("/edit")
    public String showEditForm(Model model, @RequestParam Long id) {
        EmployeeData employeeData = employeeDataService.findById(id);
        model.addAttribute("employeeData", employeeData);
        return "empData/edit";

    }

    @PostMapping("/edited")
    public String finalizeEdit(EmployeeData employeeData, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "empData/edit";
        }

        employeeDataService.update(employeeData);
        return "done";
    }


}
