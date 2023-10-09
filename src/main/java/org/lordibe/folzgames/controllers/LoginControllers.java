package org.lordibe.folzgames.controllers;

import jakarta.servlet.http.HttpSession;
import org.lordibe.folzgames.repositries.AdminRepository;
import org.lordibe.folzgames.repositries.UserRepository;
import org.lordibe.folzgames.services.AdminService;
import org.lordibe.folzgames.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginControllers {
    private AdminRepository adminRepository;
    private AdminService adminService;

    private UserRepository userRepository;
    private UserService userService;

    private HttpSession session;

    @Autowired
    public LoginControllers(UserService userService,
                          HttpSession session,
                          UserRepository userRepository,
                            AdminService adminService,
                            AdminRepository adminRepository) {

        this.userService = userService;
        this.session = session;
        this.userRepository = userRepository;
        this.adminService = adminService;
        this.adminRepository = adminRepository;
    }


    // USER
    @GetMapping("/user-login-page")
    public String userLoginPage() {
        return "user-login-page";
    }

    @PostMapping("/login-user")
    public String loginUser(@RequestParam("username") String email, @RequestParam("password") String password) {
        Boolean isValidUser = userService.loginAuthentication(email, password);

        if(isValidUser) {
            session.setAttribute("id", userRepository.findFirstByEmail(email).orElse(null).getId());
            session.setAttribute("name", userRepository.findFirstByEmail(email).orElse(null).getFirstname());

            return "redirect:/home-page";
        } else {
            return "redirect:/user-login-page";
        }
    }



    // ADMIN
    @GetMapping("/admin-login-page")
    public String adminLoginPage() {
        return "admin-login-page";
    }

    @PostMapping("/login-admin")
    public String login(@RequestParam("username") String email, @RequestParam("password") String password){
        Boolean isValidUser = adminService.loginAuthentication(email, password);

        if(isValidUser) {
            session.setAttribute("id", adminRepository.findFirstByEmail(email).orElse(null).getId());
            session.setAttribute("name", adminRepository.findFirstByEmail(email).orElse(null).getFirstname());

            return "redirect:/admin-home-page";
        } else {
            session.setAttribute("status", "failed");
            return "redirect:/admin-login-page";
        }
    }
}
