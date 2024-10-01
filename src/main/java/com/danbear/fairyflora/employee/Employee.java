package com.danbear.fairyflora.employee;

import com.danbear.fairyflora.branch.Branch;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Entity
@Data
@NoArgsConstructor
@Table(name = "employee")

public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String firstName;
    @NotNull
    private String middleName;
    @NotNull
    private String lastName;
    @NotNull
    private Long salary;
    @NotNull
    private Long contactNumber;
    @NotNull
    private LocalDate dateHired;
    @NotNull
    private String role;
    @NotNull
    private String emailAddress;

    @ManyToOne(cascade = CascadeType.ALL)
    private Branch branch;

}

