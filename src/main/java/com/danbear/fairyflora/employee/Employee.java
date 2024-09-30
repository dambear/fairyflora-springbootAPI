package com.danbear.fairyflora.employee;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

import java.time.LocalDate;


public record Employee(
    @Id
    Integer id,
    String firstName,
    String middleName,
    String lastName,
    String branchId,
    Integer salary,
    Integer contactNumber,
    LocalDate dateHired,
    String role,
    String emailAddress,
    @Version
    Integer version
) {}
