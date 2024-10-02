package com.danbear.fairyflora.employee;

import com.danbear.fairyflora.branch.Branch;
import com.danbear.fairyflora.branch.BranchService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

  private final EmployeeRepository employeeRepository;
  private final BranchService branchService;

  EmployeeService(EmployeeRepository employeeRepository, BranchService branchService) {
    this.employeeRepository = employeeRepository;
    this.branchService = branchService;
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

      // Update the branch if a new branch ID is provided
      if (newEmployee.getBranch() != null && newEmployee.getBranch().getId() != null) {
        Long branchId = newEmployee.getBranch().getId();
        Optional<Branch> newBranchOpt = branchService.findBranchById(branchId); // Assuming branchRepository is available

        if (newBranchOpt.isPresent()) {
          existingEmployee.setBranch(newBranchOpt.get());
        } else {
          throw new EntityNotFoundException("Branch not found with id: " + branchId);
        }
      }

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