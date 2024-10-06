package com.danbear.fairyflora.transaction;

import com.danbear.fairyflora.addon.Addon;
import com.danbear.fairyflora.addon.AddonNotFoundException;
import com.danbear.fairyflora.addon.AddonRepository;
import com.danbear.fairyflora.addon.dto.AddonDto;
import com.danbear.fairyflora.service.ServiceRepository;
import com.danbear.fairyflora.service.ServiceT;
import com.danbear.fairyflora.service.dto.ServiceTDto;
import com.danbear.fairyflora.service.item.Item;
import com.danbear.fairyflora.transaction.dto.TransactionDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class TransactionServiceImpl implements TransactionService {

  private final TransactionRepository transactionRepository;
  private final ServiceRepository serviceRepository;

  public TransactionServiceImpl(TransactionRepository transactionRepository, ServiceRepository serviceRepository ) {
    this.transactionRepository = transactionRepository;
    this.serviceRepository = serviceRepository;
  }

  public List<TransactionDto> findAllTransactions() {
    List<Transaction> transactions = transactionRepository.findAll();
    return transactions.stream().map((transaction) -> mapToDto(transaction)).collect(Collectors.toList());
  }


  public TransactionDto findTransactionById(Long id) {
    Transaction transaction = transactionRepository.findById(id).orElseThrow(
        () -> new AddonNotFoundException("Transaction not found with id: " + id));
    return mapToDto(transaction);
  }

  public TransactionDto createTransaction(TransactionDto transactionDto) {
    Transaction transaction = new Transaction();

    transaction.setLaundryServices(transactionDto.getLaundryServices());
    transaction.setAddons(transactionDto.getAddons());
    transaction.setTransactionDate(transaction.getTransactionDate());
    transaction.setCustomerFirstName(transactionDto.getCustomerFirstName());
    transaction.setCustomerLastName(transactionDto.getCustomerLastName());
    transaction.setContactNumber(transactionDto.getContactNumber());
    transaction.setTotalPrice(transactionDto.getTotalPrice());
    transaction.setTransactionStatus(transactionDto.getTransactionStatus());

    Transaction savedTransaction = transactionRepository.save(transaction);

    return mapToDto(savedTransaction);
  }

  public TransactionDto updateTransaction(TransactionDto transactionDto, Long id) {
    Transaction transaction = transactionRepository.findById(id).orElseThrow(
        () -> new AddonNotFoundException("Transaction not found with id: " + id));

    transaction.setLaundryServices(transactionDto.getLaundryServices());
    transaction.setAddons(transactionDto.getAddons());
    transaction.setTransactionDate(transaction.getTransactionDate());
    transaction.setCustomerFirstName(transactionDto.getCustomerFirstName());
    transaction.setCustomerLastName(transactionDto.getCustomerLastName());
    transaction.setContactNumber(transactionDto.getContactNumber());
    transaction.setTotalPrice(transactionDto.getTotalPrice());
    transaction.setTransactionStatus(transactionDto.getTransactionStatus());

    Transaction updatedTransaction = transactionRepository.save(transaction);

    return mapToDto(updatedTransaction);
  }

  public void deleteTransaction(Long id) {
    Transaction transaction = transactionRepository.findById(id).orElseThrow(
        () -> new AddonNotFoundException("Transaction not found with id: " + id));

    transactionRepository.delete(transaction);
  }


  public TransactionDto assignServiceToTransaction(Long transaction_id, Long service_id) {
    Set<ServiceT> serviceSet;

    Transaction transaction = transactionRepository.findById(transaction_id).orElseThrow(
        () -> new AddonNotFoundException("Transaction not found with id: " + transaction_id));

    ServiceT service = serviceRepository.findById(service_id).orElseThrow(
        () -> new AddonNotFoundException("Service not found with id: " + service_id));

    serviceSet = transaction.getLaundryServices();
    serviceSet.add(service);

    transaction.setLaundryServices(serviceSet);

    service.calculateTotalPrice();

    Transaction assign = transactionRepository.save(transaction);

    return mapToDto(assign);
  }


  // >>>>>>>>  Mappers
  public static Transaction mapToEntity(TransactionDto transaction) {
    Transaction transactionDto = Transaction.builder()
        .id(transaction.getId())
        .laundryServices(transaction.getLaundryServices())
        .Addons(transaction.getAddons())
        .transactionDate(transaction.getTransactionDate())
        .customerFirstName(transaction.getCustomerFirstName())
        .customerLastName(transaction.getCustomerLastName())
        .contactNumber(transaction.getContactNumber())
        .totalPrice(transaction.getTotalPrice())
        .transactionStatus(transaction.getTransactionStatus())
        .build();
    return transactionDto;
  }

  public static TransactionDto mapToDto(Transaction transaction) {
    TransactionDto transactionDto = TransactionDto.builder()
        .id(transaction.getId())
        .laundryServices(transaction.getLaundryServices())
        .Addons(transaction.getAddons())
        .transactionDate(transaction.getTransactionDate())
        .customerFirstName(transaction.getCustomerFirstName())
        .customerLastName(transaction.getCustomerLastName())
        .contactNumber(transaction.getContactNumber())
        .totalPrice(transaction.getTotalPrice())
        .transactionStatus(transaction.getTransactionStatus())
        .build();
    return transactionDto;
  }

}
