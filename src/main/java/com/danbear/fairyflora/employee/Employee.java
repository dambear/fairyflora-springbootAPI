package com.danbear.fairyflora.employee;

import com.danbear.fairyflora.branch.Branch;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    @NotNull @Column(unique = true)
    private String emailAddress;

    @NotNull
    private Long branchId; // Foreign key

    @ManyToOne
    @JoinColumn(name = "branchId", insertable = false, updatable = false)
    @JsonIgnoreProperties({"employees"}) // >>> @JsonBackReference // This tells Jackson to ignore this branch in the JSON output
    private Branch branch; // Reference to Branch entity

}

