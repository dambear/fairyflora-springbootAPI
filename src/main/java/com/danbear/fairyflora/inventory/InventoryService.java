package com.danbear.fairyflora.inventory;

import com.danbear.fairyflora.branch.Branch;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryService {
  private final InventoryRepository inventoryRepository;

  public InventoryService(InventoryRepository inventoryRepository) {
    this.inventoryRepository = inventoryRepository;
  }

  public List<Inventory> findAllInventories() {
    return inventoryRepository.findAll();
  }

  public Optional<Inventory> findInventoryById(Long id) {
    return inventoryRepository.findById(id);
  }

  public Inventory createInventory(Inventory inventory) {
    return inventoryRepository.save(inventory);
  }

  @Transactional
  public Inventory updateInventory(Inventory newInventory, Long id) {
    Inventory existingInventory = inventoryRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Inventory not found with id: " + id));

    // Update fields
    existingInventory.setAriel(newInventory.getAriel());
    existingInventory.setDowny(newInventory.getDowny());
    existingInventory.setZonrox(newInventory.getZonrox());

    // Save changes
    return inventoryRepository.save(existingInventory);
  }

  public void deleteInventory(Long id) {
    if (!inventoryRepository.existsById(id)) {
      throw new EntityNotFoundException("Inventory not found with id: " + id);
    }
    inventoryRepository.deleteById(id);
  }
}
