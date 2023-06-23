package pl.alekosszu.KME.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.alekosszu.KME.entity.user.Role;
import pl.alekosszu.KME.service.RoleService;

import java.util.List;

@Controller
@RequestMapping("/admin/role")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @GetMapping("/save")
    public String save(Model model){
        model.addAttribute("role", new Role());
        return "role/save";
    }

    @PostMapping("/saved")
    public String roleSaved(Role role) {
        roleService.save(role);
        return "redirect:findall";
    }

    @GetMapping("/remove")
    public String remove(@RequestParam Long id) {
        roleService.deleteById(id);
        return "redirect:findall";
    }

    @GetMapping("/findall")
    public String findAll(Model model) {
        final List<Role> roles = roleService.findAll();
        model.addAttribute("roles", roles);
        return "role/list";
    }


}
