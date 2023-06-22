package pl.alekosszu.KME.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.alekosszu.KME.entity.treatments.Category;
import pl.alekosszu.KME.service.CategoryService;

import java.util.List;

@Controller
@RequestMapping("/admin/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/save")
    public String saveCategoryForm(Model model) {
        model.addAttribute("category", new Category());
        return "category/save";
    }

    @PostMapping("/saved")
    public String categorySaved(Category category) {
        categoryService.save(category);
        return "redirect:findall";
    }

    @GetMapping("/findall")
    public String findAll(Model model) {
        final List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        return "category/list";
    }

    @GetMapping("/remove")
    public String remove(@RequestParam Long id) {
        categoryService.deleteById(id);
        return "redirect:findall";
    }

    @GetMapping("/edit")
    public String showEditForm(Model model, @RequestParam Long id) {
        Category category = categoryService.findById(id);
        model.addAttribute("category", category);
        return "category/edit";

    }

    @PostMapping("/edited")
    public String finalizeEdit(Category category, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "category/edit";
        }

        categoryService.update(category);
        return "redirect:findall";
    }


}
