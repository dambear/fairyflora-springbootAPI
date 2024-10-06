package com.danbear.fairyflora.addon;

public class AddonNotFoundException extends RuntimeException {
  public static final long serialVersionUID = 1;

  public AddonNotFoundException(String message) {
    super(message);
  }
}
