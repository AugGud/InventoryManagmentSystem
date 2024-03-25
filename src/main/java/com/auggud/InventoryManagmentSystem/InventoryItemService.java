package com.auggud.InventoryManagmentSystem;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

    // READ, in progress.
//    public InventoryItem getInventoryItem(Long invItemId) {
//        return invItemRepository.findById(invItemId);
//    }

    // DELETE
    public void deleteIventoryItem(Long invItemId) {
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
