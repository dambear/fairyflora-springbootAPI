package com.danbear.fairyflora.transaction;

import com.danbear.fairyflora.addon.Addon;
import com.danbear.fairyflora.branch.Branch;
import com.danbear.fairyflora.service.ServiceT;
import com.danbear.fairyflora.service.item.Item;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Data
@NoArgsConstructor
@Table(name = "transaction")
public class Transaction {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToMany
  @JoinTable(
      name = "transaction_services",
      joinColumns = @JoinColumn(name = "transaction_id"),
      inverseJoinColumns = @JoinColumn(name = "service_id")
  )
  private Set<ServiceT> laundryServices = new HashSet<>();

  @ManyToMany
  @JoinTable(
      name = "transaction_addon",
      joinColumns = @JoinColumn(name = "transaction_id"),
      inverseJoinColumns = @JoinColumn(name = "addon_id")
  )
  private List<Addon> Addons;


  @Column(nullable = false, updatable = false)
  private LocalDateTime transactionDate;
  private String customerFirstName;
  private String customerLastName;
  private Long contactNumber;
  private Long totalPrice;
  private TransactionStatus transactionStatus;

  @ManyToOne
  @JoinColumn(name = "branch_id")
  @JsonIgnoreProperties({"employees"}) // >>> @JsonBackReference // This tells Jackson to ignore this branch in the JSON output
  private Branch branch; // Reference to Branch entity

  @PrePersist
  protected void onCreate() {
    this.transactionDate = LocalDateTime.now(); // Set default value before persisting
  }
}
