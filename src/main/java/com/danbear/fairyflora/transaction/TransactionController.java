package com.danbear.fairyflora.transaction;


import com.danbear.fairyflora.addon.Addon;
import com.danbear.fairyflora.addon.AddonService;
import com.danbear.fairyflora.exception.StatusObject;
import com.danbear.fairyflora.service.dto.ServiceTDto;
import com.danbear.fairyflora.transaction.dto.TransactionDto;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

  private final TransactionService transactionService;

  public TransactionController(TransactionService transactionService) {
    this.transactionService = transactionService;
  }

  @GetMapping
  public ResponseEntity<List<TransactionDto>> getAllServices() {
    List<TransactionDto> transactions = transactionService.findAllTransactions();
    return new ResponseEntity<>(transactions, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<TransactionDto> getAddonById(@PathVariable Long id) {
    return ResponseEntity.ok(transactionService.findTransactionById(id));
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<TransactionDto> createAddon(@Valid @RequestBody TransactionDto transactionDto) {
    return new ResponseEntity<>(transactionService.createTransaction(transactionDto), HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<TransactionDto> updateAddon(
      @Valid
      @RequestBody TransactionDto transactionDto,
      @PathVariable("id") Long id)
  {
    TransactionDto response = transactionService.updateTransaction(transactionDto, id);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<StatusObject> deleteAddon(
      @PathVariable("id") Long id)
  {
    transactionService.deleteTransaction(id);

    // Display Status and code
    StatusObject statusObject = new StatusObject();
    statusObject.setStatusCode(HttpStatus.OK.value());
    statusObject.setMessage("Transaction deleted with id: " + id );
    statusObject.setTimestamp(new Date());

    return new ResponseEntity<>(statusObject, HttpStatus.OK);
  }

  @PutMapping("/{transactionId}/services/{serviceId}")
  public ResponseEntity<TransactionDto> assignItemToService(
      @PathVariable Long transactionId,
      @PathVariable Long serviceId
  ) {
    TransactionDto updatedService = transactionService.assignServiceToTransaction(transactionId, serviceId);
    return ResponseEntity.ok(updatedService);
  }

}