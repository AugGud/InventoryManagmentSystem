package com.auggud.InventoryManagmentSystem;

public class InventoryItemMapper {

    public static InventoryItemDTO toDto(InventoryItem inventoryItem) {
        return new InventoryItemDTO(
            inventoryItem.getId(),
            inventoryItem.getName(),
            inventoryItem.getDescription(),
            inventoryItem.getQuantity(),
            inventoryItem.getPrice()
        );
    }

    public static InventoryItem toEntity(InventoryItemDTO dto) {
        InventoryItem entity = new InventoryItem();
        updateEntityFromDto(entity, dto);
        return entity;
    }

    public static void updateEntityFromDto(InventoryItem entity, InventoryItemDTO dto) {
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setQuantity(dto.getQuantity());
        entity.setPrice(dto.getPrice());
    }
}