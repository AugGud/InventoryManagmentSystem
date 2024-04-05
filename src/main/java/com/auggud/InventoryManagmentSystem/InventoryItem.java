package com.auggud.InventoryManagmentSystem;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "inventory_items")
class InventoryItem {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="inv_item_id")
    private Long id;

  @Column(name="name")
    private String name;
  
  @Column(name="description")
    private String description;

  @Column(name="quantity")
    private int quantity;

  @Column(name="amount")
    private BigDecimal amount;
  
  public InventoryItem(String name, String description, int quantity, BigDecimal amount) {
    this.name = name;
    this.description = description;
    this.quantity = quantity;
    this.amount = amount;
  }

  public InventoryItem() {
  }

  public Long getId() {
    return this.id;
  }
  
  public String getName() {
    return name;
  }

  public void setId(Long id) {
    this.id = id;
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

  @Override
  public String toString() {
    return "InventoryItem [id=" + id + ", name=" + name + ", description=" + description + ", quantity=" + quantity
        + ", amount=" + amount + "]";
  }
}