package org.lordibe.folzgames.controllers;

import jakarta.servlet.http.HttpSession;
import org.lordibe.folzgames.entities.Cart;
import org.lordibe.folzgames.repositries.ProductRepository;
import org.lordibe.folzgames.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Objects;

@Controller
public class CartController {
    private CartService cartService;
    private ProductRepository productRepository;

    @Autowired
    public CartController(CartService cartService, ProductRepository productRepository) {
        this.cartService = cartService;
        this.productRepository = productRepository;
    }

    @GetMapping("/add-to-cart")
    public String addToCart(@RequestParam("newQuantity") Integer newQuantity,
                            @RequestParam("prodId") Integer prodId,
                            @RequestParam("userId") Integer userId) {

        cartService.updateUserCart(newQuantity, prodId, userId);

        return "redirect:/shop-page";
    }

    @GetMapping("/increase-product")
    public String increaseProduct(@RequestParam("newQuantity") Integer newQuantity,
                            @RequestParam("prodId") Integer prodId,
                            @RequestParam("userId") Integer userId) {

        cartService.updateUserCart(newQuantity, prodId, userId);

        return "redirect:/cart-page";
    }

    @GetMapping("/decrease-product")
    public String decreaseProduct(@RequestParam("newQuantity") Integer newQuantity,
                                  @RequestParam("prodId") Integer prodId,
                                  @RequestParam("userId") Integer userId,
                                  @RequestParam("prodQty") Integer prodQty) {
        if (prodQty > 1) {
            cartService.updateUserCart(newQuantity, prodId, userId);
        } else {
            cartService.deleteByUserIdAndProdId(userId, prodId);
        }

        return "redirect:/cart-page";
    }

    @GetMapping("/delete-product")
    public String deleteProduct(@RequestParam("userId") Integer userId,
                                @RequestParam("prodId") Integer prodId) {

        cartService.deleteByUserIdAndProdId(userId, prodId);

        return "redirect:/cart-page";
    }

    @GetMapping("/cart-page")
    public String displayCustomerCart(HttpSession session, Model model) {
        int id = (int) session.getAttribute("id");

        List<Cart> userCart = cartService.getUserCartList(id);
        Double totalPrice = cartService.totalPrice(userCart);

        model.addAttribute("cart", userCart);
        model.addAttribute("totalPrice", totalPrice);

        return "cart-page";
    }
}
