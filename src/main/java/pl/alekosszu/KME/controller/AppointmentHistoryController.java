package pl.alekosszu.KME.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.alekosszu.KME.entity.user.AppointmentHistory;
import pl.alekosszu.KME.entity.user.User;
import pl.alekosszu.KME.service.AppointmentHistoryService;
import pl.alekosszu.KME.service.UserService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("/user")

public class AppointmentHistoryController {


    private final AppointmentHistoryService appointmentHistoryService;
    private final UserService userService;


    public AppointmentHistoryController(AppointmentHistoryService appointmentHistoryService, UserService userService) {
        this.appointmentHistoryService = appointmentHistoryService;
        this.userService = userService;
    }


    @GetMapping("/history")
    public String showMeMyHistory(Model model) {
        Long userId = userService.getCurrentUser();
        Collection<AppointmentHistory> allHistory = appointmentHistoryService.findAllByUserId(userId);

        List<AppointmentHistory> past = new ArrayList<>();
        List<AppointmentHistory> future = new ArrayList<>();


        for (AppointmentHistory a : allHistory) {
            if (a.getDate().isAfter(LocalDate.now())) {
                future.add(a);
            } else {
                past.add(a);
            }

        }

        model.addAttribute("past", past);
        model.addAttribute("future", future);

        System.out.println("====================================");
        System.out.println(future);
        System.out.println(past);
        return "user/history";


    }
}
