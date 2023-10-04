package org.lordibe.folzgames.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.lordibe.folzgames.entities.Admin;
import org.lordibe.folzgames.entities.User;
import org.lordibe.folzgames.repositries.AdminRepository;
import org.lordibe.folzgames.services.AdminService;
import org.lordibe.folzgames.services.serviceimplementations.AdminServiceImpl;
import org.lordibe.folzgames.services.serviceimplementations.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminController {
    private AdminService adminService;
    private AdminRepository adminRepository;

    @Autowired
    public AdminController(AdminService adminService, AdminRepository adminRepository) {
        this.adminService = adminService;
        this.adminRepository = adminRepository;
    }
}
