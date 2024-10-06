package com.danbear.fairyflora.addon.dto;


import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddonDto {
  private Long id;

  @NotEmpty
  private String itemName;

  @NotNull
  private Long price;

  @Column(nullable = false)
  private Long quantity;

  @Column(nullable = false)
  private Long totalPrice;
}
