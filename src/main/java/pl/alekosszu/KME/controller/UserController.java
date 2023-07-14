package pl.alekosszu.KME.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.alekosszu.KME.entity.employee.Employee;
import pl.alekosszu.KME.entity.employee.Specialty;
import pl.alekosszu.KME.entity.treatments.Procedure;
import pl.alekosszu.KME.entity.user.Appointment;
import pl.alekosszu.KME.entity.user.Role;
import pl.alekosszu.KME.entity.user.User;
import pl.alekosszu.KME.service.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/user/")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final ProcedureService procedureService;
    private final EmployeeService employeeService;
    private final RoleService roleService;


    @GetMapping("/save")
    public String saveUserForm(Model model) {
        model.addAttribute("user", new User());
        return "user/save";
    }

    @PostMapping("/saved")
    public String userSaved(User user) {
        Role role = roleService.findByName("user");
        user.setRole(role);
        userService.save(user);
        return "redirect:findall";
    }

    @GetMapping("/findall")
    public String findAll(Model model) {
        final List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "user/list";
    }

    @GetMapping("/remove")
    public String remove(@RequestParam Long id) {
        userService.deleteById(id);
        return "redirect:findall";
    }

    @GetMapping("/edit")
    public String showEditForm(Model model, @RequestParam Long id) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "user/edit";

    }

    @GetMapping("/edituser")
    public String showEditFormForUser(Model model, @RequestParam Long id) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "user/edituser";

    }

    @PostMapping("/edited")
    public String finalizeEdit(User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "user/edit";
        }

        userService.update(user);
        return "redirect:findall";
    }

    @PostMapping("/editedUser")
    public String finalizeEditByUser(User user, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            return "user/edituser";
//        }

        userService.update(user);
        return "user/home";
    }


    @GetMapping("/editmydata")
    public String editMyData(Model model, Principal principal) {
        String username = principal.getName(); // Pobierz nazwę zalogowanego użytkownika
        User user = userService.findByEmail(username); // Pobierz użytkownika na podstawie emaila użytkownika
        model.addAttribute("user", user);
        return "user/edituser";
    }

    @GetMapping("/displayme")
    public String displayMyData(Model model, Principal principal) {
        String username = principal.getName(); // Pobierz nazwę zalogowanego użytkownika
        User user = userService.findByEmail(username); // Pobierz użytkownika na podstawie emaila użytkownika
        String dobString = user.getDob().toString();
        model.addAttribute("dobString", dobString);
        model.addAttribute("user", user);
        return "user/displayme";

    }

    @GetMapping("/home")
    public String getUserToHisHomepage(){
        return "user/home";
    }

}
