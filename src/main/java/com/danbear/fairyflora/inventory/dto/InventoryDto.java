package com.danbear.fairyflora.inventory.dto;

import com.danbear.fairyflora.branch.Branch;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InventoryDto {
  private Long id;

  private Long ariel;
  private Long downy;
  private Long zonrox;

  @JsonIgnoreProperties({"inventory","employees"}) // Use a foreign key
  private Branch branch;
}
