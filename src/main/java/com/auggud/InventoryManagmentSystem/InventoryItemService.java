package com.auggud.InventoryManagmentSystem;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class InventoryItemService {
    private final InventoryItemRepository repository;

    public InventoryItemService(InventoryItemRepository repository) {
        this.repository = repository;
    }

    public InventoryItemDTO createInventoryItem(InventoryItemDTO dto) {
        InventoryItem entity = InventoryItemMapper.toEntity(dto);
        InventoryItem savedEntity = repository.save(entity);
        return InventoryItemMapper.toDto(savedEntity);
    }

    public Optional<InventoryItemDTO> getInventoryItemById(Long id) {
        return repository.findById(id).map(InventoryItemMapper::toDto);
    }

    public List<InventoryItemDTO> getAllInventoryItems() {
        return repository.findAll().stream()
                .map(InventoryItemMapper::toDto)
                .collect(Collectors.toList());
    }

    public Optional<InventoryItemDTO> updateInventoryItem(Long id, InventoryItemDTO dto) {
        return repository.findById(id)
                .map(entity -> {
                    InventoryItemMapper.updateEntityFromDto(entity, dto);
                    InventoryItem updatedEntity = repository.save(entity);
                    return InventoryItemMapper.toDto(updatedEntity);
                });
    }

    public void deleteInventoryItem(Long id) {
        repository.deleteById(id);
    }
}