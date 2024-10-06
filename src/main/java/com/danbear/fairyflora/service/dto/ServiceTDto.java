package com.danbear.fairyflora.service.dto;

import com.danbear.fairyflora.service.item.Item;
import lombok.Builder;
import lombok.Data;


import java.util.Set;

@Data
@Builder
public class ServiceTDto {
  private Long id;
  private String serviceName;
  private String serviceCode;
  private Long totalPrice;
  private String description;
  private Set<Item> items;
}
