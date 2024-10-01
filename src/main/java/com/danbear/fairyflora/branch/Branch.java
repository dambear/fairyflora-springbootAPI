package com.danbear.fairyflora.branch;

import com.danbear.fairyflora.employee.Employee;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "branch")

public class Branch {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotNull
  private String barangay;

  @NotNull
  private String municipality;

  @NotNull
  private String province;

  @NotNull
  private LocalTime openingTime;

  @NotNull
  private LocalTime closingTime;

  @NotNull @Column(unique = true)
  private String emailAddress;

  @NotNull
  private LocalDate dateEstablished;

  @OneToMany(mappedBy = "branch",cascade = CascadeType.ALL)
  private List<Employee> employees;

}
