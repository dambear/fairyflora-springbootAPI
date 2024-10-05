package com.danbear.fairyflora.transaction;


import com.danbear.fairyflora.addon.Addon;
import com.danbear.fairyflora.addon.AddonService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
  public ResponseEntity<List<Transaction>> getAllTransactions() {
    List<Transaction> transactions = transactionService.findAllTransactions();
    return new ResponseEntity<>(transactions, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Transaction> getTransactionById(@PathVariable Long id) {
    Optional<Transaction> transaction = transactionService.findTransactionById(id);
    return transaction.map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction transaction) {
    Transaction createdTransaction = transactionService.createTransaction(transaction);
    return new ResponseEntity<>(createdTransaction, HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Optional<Transaction>> updateTransaction(@PathVariable Long id, @RequestBody Transaction newTransaction) {
    try {
      // Update the branch and return the updated entity
      transactionService.updateTransaction(newTransaction, id);
      Optional<Transaction> updatedTransaction = transactionService.findTransactionById(id); // Retrieve the updated branch
      return ResponseEntity.ok(updatedTransaction);
    } catch (EntityNotFoundException e) {
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteTransaction(@PathVariable Long id) {
    try {
      transactionService.deleteTransaction(id);
      return ResponseEntity.noContent().build(); // 204 No Content
    } catch (EntityNotFoundException e) {
      return ResponseEntity.notFound().build();
    }
  }




}