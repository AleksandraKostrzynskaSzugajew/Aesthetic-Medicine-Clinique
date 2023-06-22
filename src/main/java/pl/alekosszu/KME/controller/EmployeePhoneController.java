//package pl.alekosszu.KME.controller;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import pl.alekosszu.KME.entity.employee.Specialty;
//
//@Controller
//@RequestMapping("/emp/phone")
//@RequiredArgsConstructor
//public class EmployeePhoneController {
//
//
//    @GetMapping("/save")
//    public String saveSpecialtyForm(Model model) {
//        model.addAttribute("specialty", new Specialty());
//        return "specialty/save";
//    }
//
//    @PostMapping("/saved")
//    public String specialtySaved(Specialty specialty) {
//        specialtyService.save(specialty);
//        return "redirect:findall";
//    }
//
//    @GetMapping("/remove")
//    public String remove(@RequestParam Long id) {
//        specialtyService.deleteById(id);
//        return "redirect:findall";
//    }
//}
