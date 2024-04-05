package com.auggud.InventoryManagmentSystem;

public class InventoryItemMapper {

    // Convert User JPA Entity into InventoryItemDto
    public static InventoryItemDTO convertEntityToDto(InventoryItem inventoryItem){
        InventoryItemDTO inventoryItemDto = new InventoryItemDTO(
            inventoryItem.getId(),
            inventoryItem.getName(),
            inventoryItem.getDescription(),
            inventoryItem.getQuantity(),
            inventoryItem.getAmount()
        );
        return inventoryItemDto;
    }

    // Convert InventoryItemDto into InventoryItem JPA Entity
    public static InventoryItem convertDtoToEntity(InventoryItemDTO inventoryItemDto){
        InventoryItem inventoryItem = new InventoryItem();
        inventoryItem.setId(inventoryItemDto.getId());
        inventoryItem.setName(inventoryItemDto.getName());
        inventoryItem.setDescription(inventoryItemDto.getDescription());
        inventoryItem.setQuantity(inventoryItemDto.getQuantity());
        inventoryItem.setAmount(inventoryItemDto.getAmount());
        return inventoryItem;
    }
}
