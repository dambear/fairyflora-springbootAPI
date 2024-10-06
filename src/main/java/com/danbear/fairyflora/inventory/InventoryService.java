package com.danbear.fairyflora.inventory;

import com.danbear.fairyflora.addon.dto.AddonDto;
import com.danbear.fairyflora.branch.Branch;
import com.danbear.fairyflora.inventory.dto.InventoryDto;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface InventoryService {
  List<InventoryDto> findAllInventories();
  InventoryDto findInventoryById(Long id);
  InventoryDto createInventory(InventoryDto inventoryDto);
  InventoryDto updateInventory(InventoryDto inventoryDto, Long id);
  void deleteInventory(Long id);
}
