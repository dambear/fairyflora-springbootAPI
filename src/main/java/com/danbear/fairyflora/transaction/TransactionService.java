package com.danbear.fairyflora.transaction;

import com.danbear.fairyflora.addon.Addon;
import com.danbear.fairyflora.addon.AddonRepository;
import com.danbear.fairyflora.service.ServiceRepository;
import com.danbear.fairyflora.service.ServiceT;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TransactionService {

  private final TransactionRepository transactionRepository;
  private final AddonRepository addonRepository;
  private final ServiceRepository serviceRepository;

  TransactionService(TransactionRepository transactionRepository,
                     AddonRepository addonRepository,
                     ServiceRepository serviceRepository) {
    this.transactionRepository = transactionRepository;
    this.addonRepository = addonRepository;
    this.serviceRepository = serviceRepository;
  }

  public List<Transaction> findAllTransactions() {
    return transactionRepository.findAll();
  }

  public Optional<Transaction> findTransactionById(Long id) {
    return transactionRepository.findById(id);
  }

  public Transaction createTransaction(Transaction transaction) {
    List<Addon> allAddons = addonRepository.findAll();

    transaction.setAddons(allAddons);

    return transactionRepository.save(transaction);
  }

  @Transactional
  public void updateTransaction(Transaction newTransaction, Long id) {
    Optional<Transaction> existingTransactionOpt =  transactionRepository.findById(id);
    if(existingTransactionOpt.isPresent()) {
      Transaction existingTransaction = existingTransactionOpt.get();

      // Update fields of the existing branch
      existingTransaction.setCustomerFirstName(newTransaction.getCustomerFirstName());
      existingTransaction.setCustomerLastName(newTransaction.getCustomerLastName());
      existingTransaction.setContactNumber(newTransaction.getContactNumber());
      existingTransaction.setTransactionStatus(newTransaction.getTransactionStatus());

    }else {
      throw new EntityNotFoundException("Service not found with id: " + id);
    }
  }


  public void deleteTransaction(Long id) {
    transactionRepository.deleteById(id);
  }


  public Transaction assignServiceToTransaction(Long transaction_id, Long service_id) {
    Set<ServiceT> serviceSet;

    Transaction transaction =  transactionRepository.findById(transaction_id).get();
    ServiceT service = serviceRepository.findById(service_id).get();

    serviceSet = transaction.getLaundryServices();
    serviceSet.add(service);

    transaction.setLaundryServices(serviceSet);


    return transactionRepository.save(transaction);

  }




}







