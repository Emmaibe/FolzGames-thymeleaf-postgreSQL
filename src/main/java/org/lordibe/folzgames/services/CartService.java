package org.lordibe.folzgames.services;

import org.lordibe.folzgames.entities.Cart;

import java.util.List;

public interface CartService {
    void updateUserCart(Integer newQuantity, Integer prodId, Integer userId);

    List<Cart> getUserCartList(Integer userId);

    Double totalPrice(List<Cart> userCart);
}
