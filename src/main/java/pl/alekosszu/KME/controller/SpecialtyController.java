package pl.alekosszu.KME.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.alekosszu.KME.entity.employee.Specialty;
import pl.alekosszu.KME.entity.treatments.Category;
import pl.alekosszu.KME.service.CategoryService;
import pl.alekosszu.KME.service.SpecialtyService;

import java.util.List;

@Controller
@RequestMapping("/admin/specialty")
@RequiredArgsConstructor
public class SpecialtyController {

    private final SpecialtyService specialtyService;

    @GetMapping("/save")
    public String saveSpecialtyForm(Model model) {
        model.addAttribute("specialty", new Specialty());
        return "specialty/save";
    }

    @PostMapping("/saved")
    public String specialtySaved(Specialty specialty) {
        specialtyService.save(specialty);
        return "redirect:findall";
    }

    @GetMapping("/findall")
    public String findAll(Model model) {
        final List<Specialty> specialties = specialtyService.findAll();
        model.addAttribute("specialties", specialties);
        return "specialty/list";
    }

    @GetMapping("/remove")
    public String remove(@RequestParam Long id) {
        specialtyService.deleteById(id);
        return "redirect:findall";
    }

    @GetMapping("/edit")
    public String showEditForm(Model model, @RequestParam Long id) {
        Specialty specialty = specialtyService.findById(id);
        model.addAttribute("specialty", specialty);
        return "specialty/edit";

    }

    @PostMapping("/edited")
    public String finalizeEdit(Specialty specialty, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "specialty/edit";
        }

        specialtyService.update(specialty);
        return "redirect:findall";
    }


}
