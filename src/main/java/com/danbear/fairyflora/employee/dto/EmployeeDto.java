package com.danbear.fairyflora.employee.dto;

import com.danbear.fairyflora.branch.Branch;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class EmployeeDto {

  private Long id;

  private String firstName;

  private String middleName;

  private String lastName;

  private Long salary;

  private Long contactNumber;

  private LocalDate dateHired;

  private String role;

  private String emailAddress;

  @JsonIgnoreProperties({"employees"})
  private Branch branch;
}
