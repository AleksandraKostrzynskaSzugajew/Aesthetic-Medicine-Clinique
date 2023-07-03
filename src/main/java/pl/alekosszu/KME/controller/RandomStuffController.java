package pl.alekosszu.KME.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.alekosszu.KME.entity.user.Role;
import pl.alekosszu.KME.entity.user.User;
import pl.alekosszu.KME.service.RoleService;

@Controller
@RequiredArgsConstructor
public class RandomStuffController {

    private final RoleService roleService;

    @GetMapping("/admin/home")
    public String welcomeHomeUser() {
        return "adminHome";
    }

    @GetMapping("/forall/logout")
    public String logMeOut() {
        return "login";
    }


}
