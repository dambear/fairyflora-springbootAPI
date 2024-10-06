package com.danbear.fairyflora.inventory;

public class InventoryNotFoundException extends RuntimeException {
  public static final long serialVersionUID = 1;

  public InventoryNotFoundException(String message) {
    super(message);
  }
}