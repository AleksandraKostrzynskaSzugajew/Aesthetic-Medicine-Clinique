package pl.alekosszu.KME.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
        return "/category/list";
    }


}
