package com.danbear.fairyflora.service;

import com.danbear.fairyflora.service.servicelist.ServiceList;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;


@Entity
@Data
@NoArgsConstructor
@Table(name = "service")
public class ServiceT {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String serviceName;

  @ManyToMany(mappedBy = "services")
  private Set<ServiceList> assignedServiceLists = new HashSet<>();

  private String serviceCode;
  private Long totalPrice = 0L;
  private String description;


  public void calculateTotalPrice() {
    long total = assignedServiceLists.stream()
        .mapToLong(ServiceList::getPrice) // Use getPrice() directly since it's Long
        .sum();

    this.totalPrice = total; // Assign the total as Long
  }

}
