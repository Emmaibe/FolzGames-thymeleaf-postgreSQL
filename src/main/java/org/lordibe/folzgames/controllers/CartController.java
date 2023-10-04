package org.lordibe.folzgames.controllers;

import org.lordibe.folzgames.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CartController {
    private CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }


    @GetMapping("/cart-page")
    public String cartPage() {
        return "/cart-page";
    }

    @GetMapping("/add-to-cart")
    public String addToCart(@RequestParam("newQuantity") Integer newQuantity,
                            @RequestParam("prodId") Integer prodId,
                            @RequestParam("userId") Integer userId) {

        cartService.updateUserCart(newQuantity, prodId, userId);

        return "redirect:/shop-page";
    }

}
