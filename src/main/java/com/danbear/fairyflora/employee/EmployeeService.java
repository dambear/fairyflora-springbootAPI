package com.danbear.fairyflora.employee;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

  private final EmployeeRepository employeeRepository;

  EmployeeService(EmployeeRepository employeeRepository) {
    this.employeeRepository = employeeRepository;
  }

  public List<Employee> findAllEmployees() {
    return employeeRepository.findAll();
  }

  public Optional<Employee> findEmployeeById(Long id) {
    return employeeRepository.findById(id);
  }

  public Employee createEmployee(Employee employee) {
    return employeeRepository.save(employee);
  }

  @Transactional
  public void updateEmployee(Employee newEmployee, Long id) {
    Optional<Employee> existingEmployeeOpt =  employeeRepository.findById(id);
    if(existingEmployeeOpt.isPresent()) {
      Employee existingEmployee = existingEmployeeOpt.get();

      // Update fields of the existing branch
      existingEmployee.setFirstName(newEmployee.getFirstName());
      existingEmployee.setMiddleName(newEmployee.getMiddleName());
      existingEmployee.setLastName(newEmployee.getLastName());
      existingEmployee.setSalary(newEmployee.getSalary());
      existingEmployee.setContactNumber(newEmployee.getContactNumber());
      existingEmployee.setDateHired(newEmployee.getDateHired());
      existingEmployee.setRole(newEmployee.getRole());
      existingEmployee.setEmailAddress(newEmployee.getEmailAddress());

      // Save the updated entity
      employeeRepository.save(existingEmployee);
    }else {
      throw new EntityNotFoundException("Employee not found with id: " + id);
    }
  }


  public void deleteEmployee(Long id) {
    employeeRepository.deleteById(id);
  }
}