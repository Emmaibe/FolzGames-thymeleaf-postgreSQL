package org.lordibe.folzgames.repositries;

import jakarta.transaction.Transactional;
import org.lordibe.folzgames.entities.Cart;
import org.lordibe.folzgames.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    List<Cart> findByUserId(Integer id);
    Optional<Cart> findCartByUserIdAndProdId(Integer userId, Integer prodId);

    @Modifying
    @Transactional
    @Query("UPDATE Cart c SET c.quantity = c.quantity + :newQuantity WHERE c.prodId = :prodId AND c.userId = :userId")
    void updateQuantityByProdIdAndUserId(@Param("newQuantity") Integer newQuantity,
                                         @Param("prodId") Integer prodId,
                                         @Param("userId") Integer userId);
}
