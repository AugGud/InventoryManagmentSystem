package com.auggud.InventoryManagmentSystem;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/inventory-items")
class IventoryItemController {

    private final InventoryItemService invItemService;

    public IventoryItemController(InventoryItemService invItemService) {
        this.invItemService = invItemService;
    }

    @PostMapping
    public InventoryItem createInventoryItem(@RequestBody InventoryItem invItem) {
    return invItemService.createInventoryItem(invItem);
    }

    @GetMapping("/{requestedId}")
    public ResponseEntity<InventoryItem> readInventoryItemById(@PathVariable Long requestedId) {
        return invItemService.getById(requestedId);
    }

    @GetMapping
    public List<InventoryItem> readInventoryItems() {
        return invItemService.getInventoryItems();
    }

    @PutMapping("/{invItemId}")
    public InventoryItem readInventoryItem(@PathVariable(value = "invItemId") Long id, @RequestBody InventoryItem invItemDetails) {
        return invItemService.updateInventoryItem(id, invItemDetails);
    }

    @DeleteMapping("/{invItemId}")
    public void deleteInventoryItem(@PathVariable(value = "invItemId") Long id) {
        invItemService.deleteInventoryItem(id);
    }
}