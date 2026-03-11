package com.inventory.inventory_management.controller;

import com.inventory.inventory_management.entity.Product;
import com.inventory.inventory_management.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.inventory.inventory_management.service.StoreService;

import java.util.List;

@Controller
public class ProductController {

    private final ProductService productService;
    private final StoreService storeService;

    public ProductController(ProductService productService, StoreService storeService) {
        this.productService = productService;
        this.storeService = storeService;
    }


    // View all products
    @GetMapping("/products")
    public String listProducts(Model model) {

        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);

        return "products";
    }

    @GetMapping("/products/new")
    public String createProductForm(Model model) {

        model.addAttribute("product", new Product());
        model.addAttribute("stores", storeService.getAllStores());

        return "create_product";
    }

    @PostMapping("/products")
    public String saveProduct(@ModelAttribute("product") Product product) {

        productService.saveProduct(product);

        return "redirect:/products";
    }

    @GetMapping("/products/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {

        Product product = productService.getProductById(id);

        model.addAttribute("product", product);

        return "edit_product";
    }

    @PostMapping("/products/{id}")
    public String updateProduct(@PathVariable Long id,
                                @ModelAttribute("product") Product product) {

        Product existingProduct = productService.getProductById(id);

        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setQuantity(product.getQuantity());

        productService.saveProduct(existingProduct);

        return "redirect:/products";
    }

    @GetMapping("/products/{id}")
    public String deleteProduct(@PathVariable Long id) {

        productService.deleteProductById(id);

        return "redirect:/products";
    }

    @GetMapping("/products/low-stock")
    public String showLowStockProducts(Model model) {

        model.addAttribute("products", productService.getLowStockProducts());

        return "low_stock_products";
    }

    @GetMapping("/products/search")
    public String searchProducts(@RequestParam("keyword") String keyword, Model model) {

        model.addAttribute("products", productService.searchProducts(keyword));

        return "products";
    }

    @GetMapping("/products/add-stock/{id}")
    public String showAddStockForm(@PathVariable Long id, Model model) {

        Product product = productService.getProductById(id);

        model.addAttribute("product", product);

        return "add_stock";
    }

    @PostMapping("/products/add-stock/{id}")
    public String addStock(@PathVariable Long id, @RequestParam int quantity) {

        productService.addStock(id, quantity);

        return "redirect:/products";
    }


}