package com.danbear.fairyflora.employee;


import com.danbear.fairyflora.employee.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {
  List<EmployeeDto> findAllEmployees();
  EmployeeDto findEmployeeById(Long id);
  EmployeeDto createEmployee(EmployeeDto employeeDto);
  EmployeeDto updateEmployee(EmployeeDto employeeDto, Long id);
  void deleteEmployee(Long id);
}