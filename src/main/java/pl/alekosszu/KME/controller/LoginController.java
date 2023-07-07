package pl.alekosszu.KME.controller;

import lombok.RequiredArgsConstructor;
import org.hibernate.dialect.unique.CreateTableUniqueDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.alekosszu.KME.service.UserService;

@Controller
@RequiredArgsConstructor
public class LoginController {


    @GetMapping("/login")
    public String logMeIn(Model model) {
        return "login";
    }

    @GetMapping("/someuser/loggedin")
    public String loggedInFromForm() {
        return "loggedin";
    }

    @GetMapping("/user/loggedin")
    public String loggedInFromFormAsUser() {
        return "user/home";
    }


    @GetMapping("/admin/homee")
    public String getAdminOptions() {
        return "adminHome";
    }


    @GetMapping("/logout")
    public String logout(){
        return "login";
    }
}