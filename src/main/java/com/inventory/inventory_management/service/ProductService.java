package com.inventory.inventory_management.service;

import com.inventory.inventory_management.entity.Product;
import com.inventory.inventory_management.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // Get all products
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> getLowStockProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .filter(product -> product.getQuantity() < 5)
                .toList();
    }

    // Save product
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    // Get product by id
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    // Delete product
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

    public List<Product> searchProducts(String keyword) {
        return productRepository.findByNameContainingIgnoreCase(keyword);
    }

    public void addStock(Long id, int quantity) {

        Product product = productRepository.findById(id).orElse(null);

        if (product != null) {
            product.setQuantity(product.getQuantity() + quantity);
            productRepository.save(product);
        }
    }

}