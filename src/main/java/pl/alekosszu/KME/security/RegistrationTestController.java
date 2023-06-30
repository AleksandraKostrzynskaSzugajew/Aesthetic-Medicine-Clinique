package pl.alekosszu.KME.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.alekosszu.KME.entity.user.User;
import pl.alekosszu.KME.repository.UserRepository;

@Controller
@RequestMapping("/test")
@RequiredArgsConstructor
public class RegistrationTestController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping
    @ResponseBody
    public boolean createTestUser(){

        User user = new User();
        user.setEmail("admin@gmail.com");

        user.setPassword(passwordEncoder.encode("admin"));

        userRepository.save(user);

        return true;
    }

    @GetMapping(path = "/proba")
    String proba(){
        return "forall/all";
    }


}