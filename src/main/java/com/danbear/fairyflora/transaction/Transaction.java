package com.danbear.fairyflora.transaction;

import com.danbear.fairyflora.addon.Addon;
import com.danbear.fairyflora.service.ServiceT;

import java.time.LocalDateTime;
import java.util.List;

public class Transaction {
  private Long id;
  private List<ServiceT> laundryServiceTS;
  private List<Addon> Addon;
  private Long branchId;
  private TransactionStatus transactionStatus;
  private LocalDateTime transactionDate;
  private String customerFirstName;
  private String customerlastName;
  private Long contactNumber;
  private Long totalPrice;
}
