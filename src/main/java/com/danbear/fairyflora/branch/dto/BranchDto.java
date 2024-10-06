package com.danbear.fairyflora.branch.dto;

import com.danbear.fairyflora.employee.Employee;
import com.danbear.fairyflora.inventory.Inventory;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@Builder
public class BranchDto {
  private Long id;

  @NotEmpty
  private String barangay;

  @NotEmpty
  private String municipality;

  @NotEmpty
  private String province;

  @NotNull
  private LocalTime openingTime;

  @NotNull
  private LocalTime closingTime;

  @NotEmpty @Column(unique = true)
  private String emailAddress;

  @NotNull
  private LocalDate dateEstablished;

  @JsonIgnoreProperties({"employees", "branch"})
  private List<Employee> employees;

  @JsonIgnoreProperties("branch")
  private Inventory inventory;

}
