package com.danbear.fairyflora.exception;

import com.danbear.fairyflora.addon.AddonNotFoundException;
import com.danbear.fairyflora.branch.BranchNotFoundException;
import com.danbear.fairyflora.employee.EmployeeNotFoundException;
import com.danbear.fairyflora.inventory.InventoryNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(AddonNotFoundException.class)
  public ResponseEntity<StatusObject> handleAddonNotFoundException(AddonNotFoundException e, WebRequest request) {

    StatusObject statusObject = new StatusObject();

    statusObject.setStatusCode(HttpStatus.NOT_FOUND.value());
    statusObject.setMessage(e.getMessage());
    statusObject.setTimestamp(new Date());

    return new ResponseEntity<>(statusObject, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(BranchNotFoundException.class)
  public ResponseEntity<StatusObject> handleBranchNotFoundException(BranchNotFoundException e, WebRequest request) {

    StatusObject statusObject = new StatusObject();

    statusObject.setStatusCode(HttpStatus.NOT_FOUND.value());
    statusObject.setMessage(e.getMessage());
    statusObject.setTimestamp(new Date());

    return new ResponseEntity<>(statusObject, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(EmployeeNotFoundException.class)
  public ResponseEntity<StatusObject> handleEmployeeNotFoundException(EmployeeNotFoundException e, WebRequest request) {

    StatusObject statusObject = new StatusObject();

    statusObject.setStatusCode(HttpStatus.NOT_FOUND.value());
    statusObject.setMessage(e.getMessage());
    statusObject.setTimestamp(new Date());

    return new ResponseEntity<>(statusObject, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(InventoryNotFoundException.class)
  public ResponseEntity<StatusObject> handleInventoryNotFoundException(InventoryNotFoundException e, WebRequest request) {

    StatusObject statusObject = new StatusObject();

    statusObject.setStatusCode(HttpStatus.NOT_FOUND.value());
    statusObject.setMessage(e.getMessage());
    statusObject.setTimestamp(new Date());

    return new ResponseEntity<>(statusObject, HttpStatus.NOT_FOUND);
  }





}
