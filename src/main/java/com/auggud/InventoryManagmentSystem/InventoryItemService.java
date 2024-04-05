package com.auggud.InventoryManagmentSystem;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryItemService {
    
    @Autowired
    InventoryItemRepository invItemRepository;

    // CREATE
    public InventoryItem createInventoryItem(InventoryItemDTO invItemDto) {
        InventoryItem invItem = InventoryItemMapper.convertDtoToEntity(invItemDto);
        return invItemRepository.save(invItem);
    }

    // READ by id
    public Optional<InventoryItem> getInventoryItemById(Long requestedId) {
        return invItemRepository.findById(requestedId);
    }

    // READ all
    public List<InventoryItemDTO> getAllInventoryItems() {
        List<InventoryItem> inventoryItems = invItemRepository.findAll();
        return inventoryItems.stream()
                .map(InventoryItemMapper::convertEntityToDto)
                .collect(Collectors.toList());
    }

    // UPDATE
    public Optional<InventoryItemDTO> updateInventoryItem(Long id, InventoryItemDTO inventoryItemDto) {
        Optional<InventoryItem> inventoryItemOptional = invItemRepository.findById(id);
        if (inventoryItemOptional.isPresent()) {
            InventoryItem inventoryItem = inventoryItemOptional.get();
            inventoryItem.setName(inventoryItemDto.getName());
            inventoryItem.setDescription(inventoryItemDto.getDescription());
            inventoryItem.setQuantity(inventoryItemDto.getQuantity());
            inventoryItem.setAmount(inventoryItemDto.getAmount());
            InventoryItem updatedInventoryItem = invItemRepository.save(inventoryItem);
            return Optional.of(InventoryItemMapper.convertEntityToDto(updatedInventoryItem));
        } else {
            return Optional.empty();
        }
    }

    // DELETE
    public void deleteInventoryItem(Long id) {
        invItemRepository.deleteById(id);
    }
}
