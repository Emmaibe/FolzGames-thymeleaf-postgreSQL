package org.lordibe.folzgames.controllers;

import jakarta.servlet.http.HttpSession;
import org.lordibe.folzgames.entities.Admin;
import org.lordibe.folzgames.entities.User;
import org.lordibe.folzgames.services.AdminService;
import org.lordibe.folzgames.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationControllers {
    private AdminService adminService;
    private UserService userService;

    @Autowired
    public RegistrationControllers(AdminService adminService, UserService userService) {
        this.adminService = adminService;
        this.userService = userService;
    }

    // USER
    @GetMapping("/user-registration-page")
    public String userRegistrationPage() {
        return "user-registration-page";
    }

    @GetMapping("/user-register")
    public String registerUser(Model model) {
        model.addAttribute("user", new User());

        return "redirect:/user-registration-page";
    }

    @PostMapping("/user-register")
    public String register(@ModelAttribute User user, HttpSession session) {
        System.out.println("register request" + user);

        User registeredUser = userService.registerUser(
                user.getFirstname(),
                user.getLastname(),
                user.getEmail(),
                user.getPassword(),
                user.getPhone()
        );

        if (registeredUser == null) {
            session.setAttribute("status", "failed");
            return "redirect:/user-registration-page";
        } else {
            session.setAttribute("status", "success");
            return "redirect:/user-login-page";
        }
    }

    // ADMIN
    @GetMapping("/admin-registration-page")
    public String adminRegistrationPage() {
        return "admin-registration-page";
    }

    @GetMapping("/admin-register")
    public String registerAdmin(Model model) {
        model.addAttribute("admin", new Admin());

        return "redirect:/admin-registration-page";
    }

    @PostMapping("/admin-register")
    public String register(@ModelAttribute Admin admin, HttpSession session) {
        System.out.println("register request" + admin);

        Admin registeredAdmin = adminService.registerAdmin(
                admin.getFirstname(),
                admin.getLastname(),
                admin.getEmail(),
                admin.getPassword(),
                admin.getPhone()
        );

        if (registeredAdmin == null) {
            session.setAttribute("status", "failed");
            return "redirect:/admin-registration-page";
        } else {
            session.setAttribute("status", "success");
            return "redirect:/admin-login-page";
        }
    }
}
