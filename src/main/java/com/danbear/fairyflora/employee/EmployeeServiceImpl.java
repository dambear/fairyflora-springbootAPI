package com.danbear.fairyflora.employee;

import com.danbear.fairyflora.addon.AddonNotFoundException;
import com.danbear.fairyflora.branch.Branch;
import com.danbear.fairyflora.branch.BranchNotFoundException;
import com.danbear.fairyflora.branch.BranchRepository;
import com.danbear.fairyflora.employee.dto.EmployeeDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

  private final EmployeeRepository employeeRepository;
  private final BranchRepository branchRepository;

  public EmployeeServiceImpl(EmployeeRepository employeeRepository, BranchRepository branchRepository) {
    this.employeeRepository = employeeRepository;
    this.branchRepository = branchRepository;
  }

  public List<EmployeeDto> findAllEmployees() {
    List<Employee> employees = employeeRepository.findAll();
    return employees.stream().map((employee) -> mapToDto(employee)).collect(Collectors.toList());
  }

  public EmployeeDto findEmployeeById(Long id) {
    Employee employee = employeeRepository.findById(id).orElseThrow(
        () -> new EmployeeNotFoundException("Employee not found with id: " + id));
    return mapToDto(employee);
  }

  public EmployeeDto createEmployee(EmployeeDto employeeDto) {

    Long branch_id = employeeDto.getBranch().getId();

    Branch branch = branchRepository.findById(branch_id).orElseThrow(
        () -> new EmployeeNotFoundException("Branch not found with id: " + branch_id));

    Employee employee = new Employee();

    employee.setFirstName(employeeDto.getFirstName());
    employee.setMiddleName(employeeDto.getMiddleName());
    employee.setLastName(employeeDto.getLastName());
    employee.setSalary(employeeDto.getSalary());
    employee.setContactNumber(employeeDto.getContactNumber());
    employee.setDateHired(employeeDto.getDateHired());
    employee.setRole(employeeDto.getRole());
    employee.setEmailAddress(employeeDto.getEmailAddress());
    employee.setBranch(branch);


    Employee savedBranch = employeeRepository.save(employee);


    return mapToDto(savedBranch);
  }


  public EmployeeDto updateEmployee(EmployeeDto employeeDto, Long id) {

    Long branch_id = employeeDto.getBranch().getId();

    Branch branch = branchRepository.findById(branch_id).orElseThrow(
        () -> new BranchNotFoundException("Branch not found with id: " + branch_id));

    Employee employee = employeeRepository.findById(id).orElseThrow(
        () -> new EmployeeNotFoundException("Employee not found with id: " + id));

    employee.setFirstName(employeeDto.getFirstName());
    employee.setMiddleName(employeeDto.getMiddleName());
    employee.setLastName(employeeDto.getLastName());
    employee.setSalary(employeeDto.getSalary());
    employee.setContactNumber(employeeDto.getContactNumber());
    employee.setDateHired(employeeDto.getDateHired());
    employee.setRole(employeeDto.getRole());
    employee.setEmailAddress(employeeDto.getEmailAddress());
    employee.setBranch(branch);


    Employee updatedEmployee = employeeRepository.save(employee);



    return mapToDto(updatedEmployee);
  }

  public void deleteEmployee(Long id) {
    Employee employee = employeeRepository.findById(id).orElseThrow(
        () -> new EmployeeNotFoundException("Employee not found with id: " + id));

    employeeRepository.delete(employee);
  }


  // >>>>>>>>  Mappers
  public static Employee mapToEntity(EmployeeDto employee) {
    Employee employeDto = Employee.builder()
        .id(employee.getId())
        .firstName(employee.getFirstName())
        .middleName(employee.getMiddleName())
        .lastName(employee.getLastName())
        .salary(employee.getSalary())
        .contactNumber(employee.getContactNumber())
        .dateHired(employee.getDateHired())
        .role(employee.getRole())
        .emailAddress(employee.getEmailAddress())
        .branch(employee.getBranch())
        .build();
    return employeDto;
  }

  public static EmployeeDto mapToDto(Employee employee) {
    EmployeeDto employeDto = EmployeeDto.builder()
        .id(employee.getId())
        .firstName(employee.getFirstName())
        .middleName(employee.getMiddleName())
        .lastName(employee.getLastName())
        .salary(employee.getSalary())
        .contactNumber(employee.getContactNumber())
        .dateHired(employee.getDateHired())
        .role(employee.getRole())
        .emailAddress(employee.getEmailAddress())
        .branch(employee.getBranch())
        .build();
    return employeDto;
  }



}
