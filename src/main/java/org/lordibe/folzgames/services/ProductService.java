package org.lordibe.folzgames.services;

import org.lordibe.folzgames.entities.Product;

import java.util.List;

public interface ProductService {
    public List<Product> showProducts();
    public List<Product> showProductsByCategory(String category);
    public List<Product> showProductsBySearch(String word);
}
