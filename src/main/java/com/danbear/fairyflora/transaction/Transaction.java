package com.danbear.fairyflora.transaction;

import com.danbear.fairyflora.addon.Addon;
import com.danbear.fairyflora.service.Service;

import java.time.LocalDateTime;
import java.util.List;

public record Transaction(
    Integer id,
    List<Service> laundryServices,
    Integer quantityOfLaundryService,
    List<Addon> Addon,
    Integer quantityOfAddon,
    Integer branchId,
    TransactionStatus transactionStatus,
    LocalDateTime transactionDate,
    String customerFirstName,
    String customerlastName,
    Integer contactNumber,
    Integer totalPrice
) {
}
