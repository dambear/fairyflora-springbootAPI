package com.danbear.fairyflora.inventory;

import com.danbear.fairyflora.branch.Branch;
import com.danbear.fairyflora.inventory.Inventory;
import com.danbear.fairyflora.inventory.InventoryService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/inventories")
public class InventoryController {

  private final InventoryService inventoryService;

  public InventoryController(InventoryService inventoryService) {
    this.inventoryService = inventoryService;
  }

  @GetMapping
  public ResponseEntity<List<Inventory>> getAllInventories() {
    List<Inventory> inventories = inventoryService.findAllInventories();
    return new ResponseEntity<>(inventories, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Inventory> getBranchById(@PathVariable Long id) {
    Optional<Inventory> inventory = inventoryService.findInventoryById(id);
    return inventory.map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<Inventory> createBranch(@RequestBody Inventory inventory) {
    Inventory createdInventory = inventoryService.createInventory(inventory);
    return new ResponseEntity<>(createdInventory, HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Optional<Inventory>> updateInventory(@PathVariable Long id, @RequestBody Inventory newInventory) {
    try {
      // Update the branch and return the updated entity
      inventoryService.updateInventory(newInventory, id);
      Optional<Inventory> updatedInventory = inventoryService.findInventoryById(id); // Retrieve the updated branch
      return ResponseEntity.ok(updatedInventory);
    } catch (EntityNotFoundException e) {
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteBranch(@PathVariable Long id) {
    try {
      inventoryService.deleteInventory(id);
      return ResponseEntity.noContent().build(); // 204 No Content
    } catch (EntityNotFoundException e) {
      return ResponseEntity.notFound().build();
    }
  }
}