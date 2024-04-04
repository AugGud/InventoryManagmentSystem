package com.auggud.InventoryManagmentSystem;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class InventoryItemTestDTO extends InventoryItemDTO {

    @JsonIgnore
    @Override
    public Long getId() {
        return super.getId();
    }

    @Override
    public void setId(Long id) {
        super.setId(id);
    }
}
