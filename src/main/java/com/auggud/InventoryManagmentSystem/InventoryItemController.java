package com.auggud.InventoryManagmentSystem;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/v1/inventory-items")
class IventoryItemController {

    private final InventoryItemService invItemService;

    public IventoryItemController(InventoryItemService invItemService) {
        this.invItemService = invItemService;
    }

    // CREATE Inventory Item
    // http://localhost:8080/api/v1/inventory-items
    @PostMapping
    public ResponseEntity<InventoryItemDTO> createInventoryItem(@RequestBody InventoryItemDTO invItemDto, UriComponentsBuilder ucb){
        InventoryItem savedInventoryItem = invItemService.createInventoryItem(invItemDto);
        
        URI locationOfNewInventoryItem = ucb
            .path("api/v1/inventory-items/{requestedId}")
            .buildAndExpand(savedInventoryItem.getId())
            .toUri();
    
        return ResponseEntity.created(locationOfNewInventoryItem).body(InventoryItemMapper.convertEntityToDto(savedInventoryItem));
    }

    // READ Inventory Item by id
    // http://localhost:8080/api/v1/inventory-items/{requestedId}
    @GetMapping("{requestedId}")
    public ResponseEntity<InventoryItemDTO> getInventoryItemById(@PathVariable("requestedId") Long invItemId){
        Optional<InventoryItem> invItemOptional = invItemService.getInventoryItemById(invItemId);
        if (invItemOptional.isPresent()) {
            return ResponseEntity.ok(InventoryItemMapper.convertEntityToDto(invItemOptional.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // GET All Inventory Items
    // http://localhost:8080/api/v1/inventory-items
    @GetMapping
    public ResponseEntity<List<InventoryItemDTO>> getAllInventoryItems() {
        List<InventoryItemDTO> inventoryItems = invItemService.getAllInventoryItems();
        return ResponseEntity.ok(inventoryItems);
    }

    // UPDATE Inventory Item
    // http://localhost:8080/api/v1/inventory-items/{requestedId}
    @PutMapping("{requestedId}")
    public ResponseEntity<InventoryItemDTO> updateInventoryItem(@PathVariable("requestedId") Long id, @RequestBody InventoryItemDTO inventoryItemDto) {
        Optional<InventoryItemDTO> updatedInventoryItemDto = invItemService.updateInventoryItem(id, inventoryItemDto);
        if (updatedInventoryItemDto.isPresent()) {
            return ResponseEntity.ok(updatedInventoryItemDto.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE
    // http://localhost:8080/api/v1/inventory-items
    @DeleteMapping("{requestedId}")
    public ResponseEntity<Void> deleteInventoryItem(@PathVariable("requestedId") Long id) {
        invItemService.deleteInventoryItem(id);
        return ResponseEntity.noContent().build();
    }
}