package com.danbear.fairyflora.branch;

import com.danbear.fairyflora.employee.Employee;
import com.danbear.fairyflora.inventory.Inventory;
import com.danbear.fairyflora.transaction.Transaction;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "branch")

public class Branch {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String barangay;

  private String municipality;

  private String province;

  private LocalTime openingTime;

  private LocalTime closingTime;

  private String emailAddress;

  private LocalDate dateEstablished;

  @OneToMany(mappedBy = "branch",cascade = CascadeType.ALL)
  private List<Employee> employees;

  @OneToOne(mappedBy = "branch", cascade = CascadeType.ALL)
  private Inventory inventory;

}
