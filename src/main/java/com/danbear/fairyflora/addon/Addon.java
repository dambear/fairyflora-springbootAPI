package com.danbear.fairyflora.addon;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "addon")
public class Addon {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @NotNull
  private String itemName;
  @NotNull
  private Long price;

  @Column(nullable = false)
  private Long quantity = 0L;  // Initialize to 0

  @Column(nullable = false)
  private Long totalPrice = 0L; // Initialize to 0

}
