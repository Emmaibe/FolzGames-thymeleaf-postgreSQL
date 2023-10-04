package org.lordibe.folzgames.repositries;

import org.lordibe.folzgames.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findAllByCategory(String category);
    List<Product> findAllByNameContainingIgnoreCase(String name);

    Optional<Product> findProductById(Integer prodId);
}
