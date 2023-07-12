package pl.alekosszu.KME.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.alekosszu.KME.entity.user.User;
import pl.alekosszu.KME.entity.user.Wish;
import pl.alekosszu.KME.service.UserService;

import java.time.LocalDate;
import java.time.LocalTime;

@Controller
public class WishController {

    private final UserService userService;

    public WishController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/joinwaitlist")
    public String goToWaitListForm(Model model, @RequestParam("procedureId") Long procedureId,
                                   @RequestParam("employeeId") Long employeeId,
                                   @RequestParam("appointmentDay") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate appointmentDay,
                                   @RequestParam("appointmentTime") @DateTimeFormat(pattern = "HH:mm") LocalTime appointmentTime) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        Long userId = userService.findByEmail(userEmail).getId();

        User user = userService.findById(userId); // Assuming userRepository for user retrieval

        Wish wish = new Wish();
        wish.setProcedureId(procedureId);
        wish.setEmployeeId(employeeId);
        wish.setAppointmentDay(appointmentDay);
        wish.setAppointmentTime(appointmentTime);

        user.addToWishList(wish);

        return "user/addToWaitList";
    }

}
