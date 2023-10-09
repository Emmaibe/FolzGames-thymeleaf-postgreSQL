package org.lordibe.folzgames.services;

import org.lordibe.folzgames.entities.Product;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductService {
    List<Product> showProducts();
    List<Product> showProductsByCategory(String category);
    List<Product> showProductsBySearch(String word);
    void updateProductByProdIdAndProdCat(Double newPrice,
                                         String prodName,
                                         String image,
                                         String prodCat);
    Product addProduct(String name,
                          String category,
                          Integer quantity,
                          Double price,
                          String images);
}
