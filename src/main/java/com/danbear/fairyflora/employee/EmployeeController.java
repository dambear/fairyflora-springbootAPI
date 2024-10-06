package com.danbear.fairyflora.employee;


import com.danbear.fairyflora.branch.dto.BranchDto;
import com.danbear.fairyflora.employee.dto.EmployeeDto;
import com.danbear.fairyflora.exception.StatusObject;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

  private final EmployeeService employeeService;

  public EmployeeController(EmployeeService employeeService) {
    this.employeeService = employeeService;
  }

  @GetMapping
  public ResponseEntity<List<EmployeeDto>> getAllAddons() {
    List<EmployeeDto> employees = employeeService.findAllEmployees();
    return new ResponseEntity<>(employees, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<EmployeeDto> getAddonById(@PathVariable Long id) {
    return ResponseEntity.ok(employeeService.findEmployeeById(id));
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<EmployeeDto> createAddon(@Valid @RequestBody EmployeeDto employeeDto) {
    return new ResponseEntity<>(employeeService.createEmployee(employeeDto), HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<EmployeeDto> updateAddon(
      @Valid
      @RequestBody EmployeeDto employeeDto,
      @PathVariable("id") Long id)
  {
    EmployeeDto response = employeeService.updateEmployee(employeeDto, id);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }


  @DeleteMapping("/{id}")
  public ResponseEntity<StatusObject> deleteAddon(
      @PathVariable("id") Long id)
  {
    employeeService.deleteEmployee(id);

    // Display Status and code
    StatusObject statusObject = new StatusObject();
    statusObject.setStatusCode(HttpStatus.OK.value());
    statusObject.setMessage("Employee deleted with id: " + id );
    statusObject.setTimestamp(new Date());

    return new ResponseEntity<>(statusObject, HttpStatus.OK);
  }


}