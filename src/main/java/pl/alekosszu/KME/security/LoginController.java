package pl.alekosszu.KME.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.alekosszu.KME.service.UserService;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class LoginController {

    @Autowired
    private final UserService userService;


    @GetMapping("/loggedin")
    public String loggedInFromForm(){
        return "loggedin";
    }


}