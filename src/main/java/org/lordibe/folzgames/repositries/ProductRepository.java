package org.lordibe.folzgames.repositries;

import jakarta.transaction.Transactional;
import org.lordibe.folzgames.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("SELECT p FROM Product p ORDER BY p.name")
    List<Product> findAllOrderByName();
    List<Product> findAllByCategoryOrderByName(String category);
    List<Product> findAllByNameContainingIgnoreCase(String name);

    Optional<Product> findProductById(Integer prodId);
    Optional<Product> findProductByName(String prodName);

    @Modifying
    @Transactional
    @Query("UPDATE Product p SET p.price = :newPrice, p.images = :image WHERE p.name = :prodName AND p.category = :prodCat")
    void updateProductByProdIdAndProdCat(@Param("newPrice") Double newPrice,
                                         @Param("prodName") String prodName,
                                         @Param("image") String image,
                                         @Param("prodCat") String prodCat);
}
