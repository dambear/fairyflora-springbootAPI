package com.danbear.fairyflora.branch;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import com.danbear.fairyflora.employee.Employee;

public record Branch(
    Integer id,
    String barangay,
    String municipality,
    String province,
    LocalTime openingTime,
    LocalTime closingTime,
    String emailAddress,
    List<Employee> assignedEmployees,
    LocalDate dateEstablished
) {}