package pl.alekosszu.KME.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.alekosszu.KME.entity.treatments.Procedure;
import pl.alekosszu.KME.service.ProcedureService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin/procedure")
@RequiredArgsConstructor
public class ProcedureController {

    private final ProcedureService procedureService;



    @ModelAttribute("categories")
    public List<String> categories() {
        List<String> categoriesAsString =
                procedureService.findAll().stream().map(spe -> spe.toString()).collect(Collectors.toList());
        return categoriesAsString;
    }

    @ModelAttribute("empForPro")
    public List<String> v() {
        List<String> employeesForProcedure =
                procedureService.findAll().stream().map(spe -> spe.toString()).collect(Collectors.toList());
        return employeesForProcedure;
    }




    @GetMapping("/save")
    public String saveProcedureForm(Model model) {
        model.addAttribute("procedure", new Procedure());
        return "procedure/save";
    }

    @PostMapping("/saved")
    public String procedureSaved(Procedure procedure) {
        procedureService.save(procedure);
        return "redirect:findall";
    }

    @GetMapping("/findall")
    public String findAll(Model model) {
        final List<Procedure> procedures = procedureService.findAll();
        model.addAttribute("procedures", procedures);
        return "procedure/list";
    }

    @GetMapping("/remove")
    public String remove(@RequestParam Long id) {
        procedureService.deleteById(id);
        return "redirect:findall";
    }

    @GetMapping("/edit")
    public String showEditForm(Model model, @RequestParam Long id) {
        Procedure procedure = procedureService.findById(id);
        model.addAttribute("procedure", procedure);
        return "procedure/edit";

    }

    @PostMapping("/edited")
    public String finalizeEdit(Procedure procedure, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "procedure/edit";
        }

        procedureService.update(procedure);
        return "redirect:findall";
    }

}
