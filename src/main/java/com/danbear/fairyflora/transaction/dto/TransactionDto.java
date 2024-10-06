package com.danbear.fairyflora.transaction.dto;

import com.danbear.fairyflora.addon.Addon;
import com.danbear.fairyflora.branch.Branch;
import com.danbear.fairyflora.service.ServiceT;
import com.danbear.fairyflora.transaction.TransactionStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;


@Data
@Builder
public class TransactionDto {
  private Long id;

  private Set<ServiceT> laundryServices;
  private List<Addon> Addons;
  private LocalDateTime transactionDate;
  private String customerFirstName;
  private String customerLastName;
  private Long contactNumber;
  private Long totalPrice;
  private TransactionStatus transactionStatus;

  @JsonIgnoreProperties({"employees", "inventory"})
  private Branch branch; // Reference to Branch entity
}
