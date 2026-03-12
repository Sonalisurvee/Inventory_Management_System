package com.inventory.inventory_management.repository;

import com.inventory.inventory_management.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {

}

