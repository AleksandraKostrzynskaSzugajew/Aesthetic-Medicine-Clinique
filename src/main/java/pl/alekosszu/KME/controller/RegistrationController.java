package pl.alekosszu.KME.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.alekosszu.KME.entity.user.Role;
import pl.alekosszu.KME.entity.user.User;
import pl.alekosszu.KME.service.RoleService;
import pl.alekosszu.KME.service.UserService;

@Controller
public class RegistrationController {


    private final UserService userService;
    private final RoleService roleService;

    public RegistrationController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/register")
    public String goToRegisterFormForUser(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user) {
        Role role = roleService.findByName("ROLE_USER");
        user.setRole(role);
        userService.registerNewUser(user);

        //if register successfull
        return "redirect:/login"; // Przekierowanie do strony logowania po rejestracji
    }


}