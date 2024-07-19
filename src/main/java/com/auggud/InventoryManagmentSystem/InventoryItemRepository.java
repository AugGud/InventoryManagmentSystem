package com.auggud.InventoryManagmentSystem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryItemRepository extends JpaRepository <InventoryItem, Long> {
}
