package org.lordibe.folzgames.services.serviceimplementations;

import org.lordibe.folzgames.repositries.ProductRepository;
import org.lordibe.folzgames.entities.Product;
import org.lordibe.folzgames.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> showProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> showProductsByCategory(String category) {
        return productRepository.findAllByCategory(category);
    }

    @Override
    public List<Product> showProductsBySearch(String word) {
        return productRepository.findAllByNameContainingIgnoreCase(word);
    }
}
