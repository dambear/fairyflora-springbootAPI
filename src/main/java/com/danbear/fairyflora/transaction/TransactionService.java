package com.danbear.fairyflora.transaction;

import com.danbear.fairyflora.addon.Addon;
import com.danbear.fairyflora.addon.AddonService;
import com.danbear.fairyflora.service.ServiceService;
import com.danbear.fairyflora.service.ServiceT;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

  private final TransactionRepository transactionRepository;
  private final AddonService addonService;
  private final ServiceService serviceService;

  TransactionService(TransactionRepository transactionRepository, AddonService addonService, ServiceService serviceService) {
    this.transactionRepository = transactionRepository;
    this.addonService = addonService;
    this.serviceService = serviceService;
  }

  public List<Transaction> findAllTransactions() {
    return transactionRepository.findAll();
  }

  public Optional<Transaction> findTransactionById(Long id) {
    return transactionRepository.findById(id);
  }

  public Transaction createTransaction(Transaction transaction) {
    List<Addon> allAddons = addonService.findAllAddons();

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



  public Transaction addServiceToTransaction(Long transactionId, Long serviceId) {
    Transaction transaction = transactionRepository.findById(transactionId)
        .orElseThrow(() -> new RuntimeException("Transaction not found"));

    ServiceT service = serviceService.findServiceById(serviceId)
        .orElseThrow(() -> new RuntimeException("Service not found"));

    // Add the service to the service list
    transaction.getLaundryServices().add(service);

    return transactionRepository.save(transaction);
  }

  public Transaction removeServiceFromTransaction(Long transactionId, Long serviceId) {
    Transaction transaction = transactionRepository.findById(transactionId)
        .orElseThrow(() -> new RuntimeException("Transaction not found"));

    ServiceT service = serviceService.findServiceById(serviceId)
        .orElseThrow(() -> new RuntimeException("Service not found"));

    // Remove the service from the service list
    if (!transaction.getLaundryServices().remove(service)) {
      throw new RuntimeException("Service not found in the ServiceList");
    }

    return transactionRepository.save(transaction);
  }


  public Transaction addAddonToTransaction(Long transactionId, Long addonId) {
    Transaction transaction = transactionRepository.findById(transactionId)
        .orElseThrow(() -> new RuntimeException("Transaction not found"));

    Addon addon = addonService.findAddonById(addonId)
        .orElseThrow(() -> new RuntimeException("Addon not found"));

    // Add the service to the service list
    transaction.getAddons().add(addon);

    return transactionRepository.save(transaction);
  }

  public Transaction removeAddonFromTransaction(Long transactionId, Long addonId) {
    Transaction transaction = transactionRepository.findById(transactionId)
        .orElseThrow(() -> new RuntimeException("Transaction not found"));

    Addon addon = addonService.findAddonById(addonId)
        .orElseThrow(() -> new RuntimeException("Addon not found"));

    // Remove the service from the service list
    if (!transaction.getAddons().remove(addon)) {
      throw new RuntimeException("Addon not found in the ServiceList");
    }

    return transactionRepository.save(transaction);
  }






}







