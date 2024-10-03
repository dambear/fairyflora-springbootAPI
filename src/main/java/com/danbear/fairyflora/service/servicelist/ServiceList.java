package com.danbear.fairyflora.service.servicelist;

import com.danbear.fairyflora.service.ServiceT;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Table(name = "servicelist")
public class ServiceList {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @NotNull
  private String itemName;
  @NotNull
  private Long price;

  @ManyToMany
  @JoinTable(
      name = "service_list_service",
      joinColumns = @JoinColumn(name = "service_list_id"),
      inverseJoinColumns = @JoinColumn(name = "service_id")
  )
  private Set<ServiceT> services = new HashSet<>();

}