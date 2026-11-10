package io.kenzo.learning.controllers;

import io.kenzo.learning.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final UserService userService;

    @GetMapping({"/", "/dashboard"})
    public String index(Model model) {
        model.addAttribute("stats", userService.dashboardStats());
        model.addAttribute("recentUsers", userService.recentUsers());
        return "dashboard";
    }
}
