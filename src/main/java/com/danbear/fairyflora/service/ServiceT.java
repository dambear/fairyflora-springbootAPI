package com.danbear.fairyflora.service;

import com.danbear.fairyflora.service.item.Item;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "service")
public class ServiceT {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String serviceName;


  private String serviceCode;
  private Long totalPrice = 0L;
  private String description;

  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(
      name = "service_item",
      joinColumns = @JoinColumn(name = "service_id"),
      inverseJoinColumns = @JoinColumn(name = "item_id")
  )
  private Set<Item> items = new HashSet<>();


  public void calculateTotalPrice() {
    long total = items.stream()
        .mapToLong(Item::getPrice) // Use getPrice() directly since it's Long
        .sum();

    this.totalPrice = total; // Assign the total as Long
  }

}
