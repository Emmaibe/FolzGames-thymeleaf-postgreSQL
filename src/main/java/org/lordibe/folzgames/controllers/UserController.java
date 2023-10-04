package org.lordibe.folzgames.controllers;

import jakarta.servlet.http.HttpSession;
import org.lordibe.folzgames.entities.Product;
import org.lordibe.folzgames.entities.User;
import org.lordibe.folzgames.repositries.UserRepository;
import org.lordibe.folzgames.services.ProductService;
import org.lordibe.folzgames.services.UserService;
import org.lordibe.folzgames.services.serviceimplementations.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Objects;

@Controller
public class UserController {
    private UserRepository userRepository;
    private UserService userService;
    private HttpSession session;

    private ProductService productService;

    @Autowired
    public UserController(UserService userService,
                          HttpSession session,
                          ProductService productService,
                          UserRepository userRepository) {
        this.userService = userService;
        this.session = session;
        this.productService = productService;
        this.userRepository = userRepository;
    }
}
