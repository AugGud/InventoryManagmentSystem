package com.auggud.InventoryManagmentSystem;

import java.math.BigDecimal;

public class InventoryItemDTO {
    
    private Long id;

    private String name;

    private String description;

    private int quantity;

    private BigDecimal amount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public InventoryItemDTO(Long id, String name, String description, int quantity, BigDecimal amount) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.amount = amount;
    }

    public InventoryItemDTO() {
    }

    @Override
    public String toString() {
        return "InventoryItemDTO [id=" + id + ", name=" + name + ", description=" + description + ", quantity="
                + quantity + ", amount=" + amount + "]";
    }
}
