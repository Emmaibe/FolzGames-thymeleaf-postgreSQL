package org.lordibe.folzgames.services.serviceimplementations;

import org.lordibe.folzgames.entities.Cart;
import org.lordibe.folzgames.repositries.CartRepository;
import org.lordibe.folzgames.repositries.ProductRepository;
import org.lordibe.folzgames.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {
    private CartRepository cartRepository;
    private ProductRepository productRepository;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void updateUserCart(Integer newQuantity, Integer prodId, Integer userId) {
        Cart cart = cartRepository.findCartByUserIdAndProdId(userId, prodId).orElse(null);
        if (cart != null) {
            cartRepository.updateQuantityByProdIdAndUserId(newQuantity, prodId, userId);
        } else {
            cart = new Cart();
            cart.setUserId(userId);
            cart.setProdId(prodId);
            cart.setPrice(productRepository.findProductById(prodId).get().getPrice());
            cart.setQuantity(1);

            cartRepository.save(cart);
        }
    }
}
