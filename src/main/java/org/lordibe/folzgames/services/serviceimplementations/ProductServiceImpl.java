package org.lordibe.folzgames.services.serviceimplementations;

import jakarta.transaction.Transactional;
import org.lordibe.folzgames.entities.Admin;
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

    @Override
    @Transactional
    public void updateProductByProdIdAndProdCat(Double newPrice, String prodName, String image, String prodCat) {
        productRepository.updateProductByProdIdAndProdCat(newPrice, prodName, image, prodCat);
    }

    @Override
    public Product addProduct( String name,
                                  String category,
                                  Integer quantity,
                                  Double price,
                                  String images) {

        if (productRepository.findProductByName(name).isPresent()) {
            return null;
        } else {
            Product product = new Product();

            product.setName(name);
            product.setCategory(category);
            product.setQuantity(quantity);
            product.setPrice(price);
            product.setImages(images);

            productRepository.save(product);

            return product;
        }
    }
}
