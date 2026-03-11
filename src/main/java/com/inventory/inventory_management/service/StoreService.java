package com.inventory.inventory_management.service;

import com.inventory.inventory_management.entity.Store;
import com.inventory.inventory_management.repository.StoreRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreService {

    private final StoreRepository storeRepository;

    public StoreService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    // Get all stores
    public List<Store> getAllStores() {
        return storeRepository.findAll();
    }

    // Save store
    public Store saveStore(Store store) {
        return storeRepository.save(store);
    }

    // Get store by ID
    public Store getStoreById(Long id) {
        return storeRepository.findById(id).orElse(null);
    }

    // Delete store
    public void deleteStoreById(Long id) {
        storeRepository.deleteById(id);
    }
}