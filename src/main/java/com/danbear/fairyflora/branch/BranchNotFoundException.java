package com.danbear.fairyflora.branch;

public class BranchNotFoundException extends RuntimeException {
  public static final long serialVersionUID = 1;

  public BranchNotFoundException(String message) {
    super(message);
  }
}
