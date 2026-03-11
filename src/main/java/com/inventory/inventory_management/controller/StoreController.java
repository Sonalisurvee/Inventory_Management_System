package com.inventory.inventory_management.controller;

import com.inventory.inventory_management.entity.Store;
import com.inventory.inventory_management.service.StoreService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class StoreController {

    private final StoreService storeService;

    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    // View all stores
    @GetMapping("/stores")
    public String listStores(Model model) {
        model.addAttribute("stores", storeService.getAllStores());
        return "stores";
    }

    // Show add store form
    @GetMapping("/stores/new")
    public String showCreateForm(Model model) {
        model.addAttribute("store", new Store());
        return "create_store";
    }

    // Save store
    @PostMapping("/stores")
    public String saveStore(@ModelAttribute Store store) {
        storeService.saveStore(store);
        return "redirect:/stores";
    }

    @GetMapping("/stores/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {

        Store store = storeService.getStoreById(id);
        model.addAttribute("store", store);

        return "edit_store";
    }

    @PostMapping("/stores/{id}")
    public String updateStore(@PathVariable Long id, @ModelAttribute Store store) {

        Store existingStore = storeService.getStoreById(id);

        existingStore.setName(store.getName());
        existingStore.setLocation(store.getLocation());
        existingStore.setManager(store.getManager());

        storeService.saveStore(existingStore);

        return "redirect:/stores";
    }

    @GetMapping("/stores/delete/{id}")
    public String deleteStore(@PathVariable Long id) {

        storeService.deleteStoreById(id);

        return "redirect:/stores";
    }
}