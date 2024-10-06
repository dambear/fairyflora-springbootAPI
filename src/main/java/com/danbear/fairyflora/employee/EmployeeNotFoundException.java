package com.danbear.fairyflora.employee;



public class EmployeeNotFoundException extends RuntimeException {
  public static final long serialVersionUID = 1;

  public EmployeeNotFoundException(String message) {
    super(message);
  }
}