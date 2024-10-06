package com.danbear.fairyflora.service.item.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ItemDto {
  private Long id;
  private String itemName;
  private Long price;
}
