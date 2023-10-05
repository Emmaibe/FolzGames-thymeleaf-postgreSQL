package org.lordibe.folzgames.controllers;

import org.lordibe.folzgames.entities.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class PagesController {
    @GetMapping("")
    public String homePage() {
        return "redirect:/landing-page";
    }

    @GetMapping("/home-page")
    public String home() {
        return "home-page";
    }

    @GetMapping("/landing-page")
    public String landingPage() {
        return "landing-page";
    }

}
