package org.lordibe.folzgames.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LogoutControllers {
    @GetMapping("/logout-user")
    public String userLogout(HttpSession session) {
        session.invalidate();

        return "redirect:/user-login-page";
    }

    @GetMapping("/logout-admin")
    public String adminLogout(HttpSession session) {
        session.invalidate();

        return "redirect:/admin-login-page";
    }
}
