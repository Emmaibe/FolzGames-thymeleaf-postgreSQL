package org.lordibe.folzgames.controllers;

import org.lordibe.folzgames.entities.Product;
import org.lordibe.folzgames.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ProductController {
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/shop-page")
    public String addAllProductsToPage(Model model) {
        List<Product> products = productService.showProducts();

        model.addAttribute("products", products);
        model.addAttribute("cat", "All Products");

        return "shop-page";
    }

    @GetMapping("/admin-view-products")
    public String addAllProductsToAdminPage(Model model) {
        List<Product> products = productService.showProducts();

        model.addAttribute("products", products);
        model.addAttribute("cat", "All Products");

        return "admin-view-products";
    }

    @GetMapping("/add-products-by-category-games")
    public String addProductsByGamesCategoryToPage(Model model) {
        List<Product> products = productService.showProductsByCategory("Games");

        model.addAttribute("products", products);
        model.addAttribute("cat", "Games");
        return "shop-page";
    }

    @GetMapping("/add-products-by-category-games-admin")
    public String addProductsByGamesCategoryToAdminPage(Model model) {
        List<Product> products = productService.showProductsByCategory("Games");

        model.addAttribute("products", products);
        model.addAttribute("cat", "Games");
        return "admin-view-products";
    }

    @GetMapping("/add-products-by-category-vr")
    public String addProductsByVRCategoryToPage(Model model) {
        List<Product> products = productService.showProductsByCategory("VR");

        model.addAttribute("products", products);
        model.addAttribute("cat", "VR");
        return "shop-page";
    }

    @GetMapping("/add-products-by-category-vr-admin")
    public String addProductsByVRCategoryToAdminPage(Model model) {
        List<Product> products = productService.showProductsByCategory("VR");

        model.addAttribute("products", products);
        model.addAttribute("cat", "VR");
        return "admin-view-products";
    }

    @GetMapping("/add-products-by-search")
    public String addProductToPage(@RequestParam("name-search") String search, Model model) {
        List<Product> products = productService.showProductsBySearch(search);

        model.addAttribute("products", products);

        return "shop-page";
    }

    @GetMapping("/admin-edit-product")
    public String adminEditProduct() {
        return "admin-edit-product";
    }

    @GetMapping("/admin-add-product")
    public String adminAddProduct() {
        return "admin-add-product";
    }
}
