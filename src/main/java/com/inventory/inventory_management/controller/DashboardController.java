package com.inventory.inventory_management.controller;

import com.inventory.inventory_management.service.ProductService;
import com.inventory.inventory_management.service.StoreService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    private final ProductService productService;
    private final StoreService storeService;

    public DashboardController(ProductService productService, StoreService storeService) {
        this.productService = productService;
        this.storeService = storeService;
    }

    @GetMapping("/")
    public String dashboard(Model model) {

        model.addAttribute("totalProducts", productService.getTotalProducts());
        model.addAttribute("totalStores", storeService.getTotalStores());
        model.addAttribute("lowStock", productService.getLowStockCount());

        return "dashboard";
    }
}