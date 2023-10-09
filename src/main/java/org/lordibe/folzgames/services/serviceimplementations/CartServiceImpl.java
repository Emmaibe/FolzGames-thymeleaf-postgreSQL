package org.lordibe.folzgames.services.serviceimplementations;

import jakarta.transaction.Transactional;
import org.lordibe.folzgames.entities.Cart;
import org.lordibe.folzgames.repositries.CartRepository;
import org.lordibe.folzgames.repositries.ProductRepository;
import org.lordibe.folzgames.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

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
            cart = new org.lordibe.folzgames.entities.Cart();
            cart.setUserId(userId);
            cart.setProdId(prodId);
            cart.setPrice(productRepository.findProductById(prodId).get().getPrice());
            cart.setProdCat(productRepository.findProductById(prodId).get().getCategory());
            cart.setProdName(productRepository.findProductById(prodId).get().getName());
            cart.setQuantity(1);

            cartRepository.save(cart);
        }
    }

    @Override
    public List<Cart> getUserCartList(Integer userId) {
        return cartRepository.findByUserIdOrderByProdId(userId);
    }

    @Override
    public Double totalPrice(List<Cart> userCart) {
        return userCart.stream()
                .map(cart -> cart.getQuantity() * cart.getPrice())
                .reduce((double) 0, Double::sum);

    }

    @Override
    @Transactional
    public void deleteByUserIdAndProdId(Integer userId, Integer prodId) {
        cartRepository.deleteByUserIdAndProdId(userId, prodId);
    }
}
