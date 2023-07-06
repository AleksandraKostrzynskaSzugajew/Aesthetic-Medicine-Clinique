package pl.alekosszu.KME.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.alekosszu.KME.service.UserService;

@Controller
@RequiredArgsConstructor
public class LoginController {

    @Autowired
    private final UserService userService;


    @GetMapping("/user/loggedin")
    public String loggedInFromForm(){
        return "loggedin";
    }

    @GetMapping("/admin/loggedinadm")
    public String loggedInFromFormAsAdmin(){
        return "adminloggedin";
    }


}