package com.auggud.InventoryManagmentSystem;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
class InventoryItem {

  private @Id @GeneratedValue
  @JsonIgnore
  Long id;
  private String name;
  private String description;
  private int quantity;
  private double amount;
  
  public InventoryItem(String name, String description, int quantity, double amount) {
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

  public double getAmount() {
    return amount;
  }

  public void setAmount(double amount) {
    this.amount = amount;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    result = prime * result + ((description == null) ? 0 : description.hashCode());
    result = prime * result + quantity;
    long temp;
    temp = Double.doubleToLongBits(amount);
    result = prime * result + (int) (temp ^ (temp >>> 32));
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    InventoryItem other = (InventoryItem) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    if (name == null) {
      if (other.name != null)
        return false;
    } else if (!name.equals(other.name))
      return false;
    if (description == null) {
      if (other.description != null)
        return false;
    } else if (!description.equals(other.description))
      return false;
    if (quantity != other.quantity)
      return false;
    if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "InventoryItem [id=" + id + ", name=" + name + ", description=" + description + ", quantity=" + quantity
        + ", amount=" + amount + "]";
  }
}