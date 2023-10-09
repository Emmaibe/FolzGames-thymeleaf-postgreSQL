package org.lordibe.folzgames.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.lordibe.folzgames.entities.Admin;
import org.lordibe.folzgames.entities.Product;
import org.lordibe.folzgames.entities.User;
import org.lordibe.folzgames.repositries.AdminRepository;
import org.lordibe.folzgames.services.AdminService;
import org.lordibe.folzgames.services.ProductService;
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
    private ProductService productService;

    @Autowired
    public AdminController(AdminService adminService, AdminRepository adminRepository, ProductService productService) {
        this.adminService = adminService;
        this.adminRepository = adminRepository;
        this.productService = productService;
    }

    @GetMapping("/admin-home-page")
    public String adminHome() {
        return "admin-home-page";
    }

    @GetMapping ("/admin-edit-product-page")
    public String editProductPage() {
        return "admin-edit-product-page";
    }

    @GetMapping ("/admin-add-product-page")
    public String addProductPage() {
        return "admin-add-product-page";
    }

    @PostMapping("/admin-edit-product")
    public String adminEditProduct(@RequestParam("productName") String prodName,
                                   @RequestParam("category") String category,
                                   @RequestParam("price") Double price,
                                   @RequestParam("image") String image) {

        productService.updateProductByProdIdAndProdCat(price, prodName, image, category);

        return "redirect:/admin-edit-product-page";
    }

    @PostMapping("/admin-add-product")
    public String adminAddProduct(@ModelAttribute Product product, Model model) {
        System.out.println("register request" + product);

        Product newProduct = productService.addProduct(
                product.getName(),
                product.getCategory(),
                product.getQuantity(),
                product.getPrice(),
                product.getImages()
        );

        if (newProduct == null) {
            model.addAttribute("status", "failed");
            return "redirect:/admin-add-product-page";
        } else {
            model.addAttribute("status", "success");
            return "redirect:/admin-add-product-page";
        }
    }
}
