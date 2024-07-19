package com.auggud.InventoryManagmentSystem;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity

public class InventoryItem {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private int quantity;

    private BigDecimal price;
  
  public InventoryItem(String name, String description, int quantity, BigDecimal price) {
    this.name = name;
    this.description = description;
    this.quantity = quantity;
    this.price = price;
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

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  @Override
  public String toString() {
    return "InventoryItem [id=" + id + ", name=" + name + ", description=" + description + ", quantity=" + quantity
        + ", price=" + price + "]";
  }
}