package com.auggud.InventoryManagmentSystem;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class InventoryItemService {
    
    @Autowired
    InventoryItemRepository invItemRepository;

    // CREATE 
    public InventoryItem createInventoryItem(InventoryItem invItem) {
        return invItemRepository.save(invItem);
    }

    // READ
    public List<InventoryItem> getInventoryItems() {
        return invItemRepository.findAll();
    }

    // READ by id
    public ResponseEntity<InventoryItem> getById(Long requestedId) {
        Optional<InventoryItem> invItemOptional = invItemRepository.findById(requestedId);
        if (invItemOptional.isPresent()) {
            return ResponseEntity.ok(invItemOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
   }

    // DELETE
    public void deleteInventoryItem(Long invItemId) {
        invItemRepository.deleteById(invItemId);
    }

    // UPDATE
    public InventoryItem updateInventoryItem(Long invItemId, InventoryItem inventoryItemDetails) {
        InventoryItem invItem = invItemRepository.findById(invItemId).get();
        invItem.setName(inventoryItemDetails.getName());
        invItem.setDescription(inventoryItemDetails.getDescription());
        invItem.setQuantity(inventoryItemDetails.getQuantity());
        invItem.setAmount(inventoryItemDetails.getAmount());
        
        return invItemRepository.save(invItem);                                
    }
}
