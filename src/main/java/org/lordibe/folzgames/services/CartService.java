package org.lordibe.folzgames.services;

public interface CartService {
    void updateUserCart(Integer newQuantity, Integer prodId, Integer userId);
}
