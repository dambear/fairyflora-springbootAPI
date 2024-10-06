package com.danbear.fairyflora.transaction;

import com.danbear.fairyflora.addon.Addon;
import com.danbear.fairyflora.addon.AddonRepository;
import com.danbear.fairyflora.service.ServiceRepository;
import com.danbear.fairyflora.service.ServiceT;
import com.danbear.fairyflora.service.dto.ServiceTDto;
import com.danbear.fairyflora.transaction.dto.TransactionDto;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface TransactionService {

  List<TransactionDto> findAllTransactions();
  TransactionDto findTransactionById(Long id);
  TransactionDto createTransaction(TransactionDto transactionDto);
  TransactionDto updateTransaction(TransactionDto transactionDto, Long id);
  void deleteTransaction(Long id);
  TransactionDto assignServiceToTransaction(Long service_id, Long item_id);


}







