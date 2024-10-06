package com.danbear.fairyflora.inventory;

import com.danbear.fairyflora.addon.AddonNotFoundException;
import com.danbear.fairyflora.addon.AddonService;
import com.danbear.fairyflora.branch.Branch;
import com.danbear.fairyflora.branch.BranchRepository;
import com.danbear.fairyflora.employee.EmployeeNotFoundException;
import com.danbear.fairyflora.inventory.dto.InventoryDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InventoryServiceImpl implements InventoryService {

  private final InventoryRepository inventoryRepository;
  private final BranchRepository branchRepository;

  public InventoryServiceImpl(InventoryRepository inventoryRepository, BranchRepository branchRepository) {
    this.inventoryRepository = inventoryRepository;
    this.branchRepository = branchRepository;
  }

  public List<InventoryDto> findAllInventories() {
    List<Inventory> addons = inventoryRepository.findAll();
    return addons.stream().map((inventory) -> mapToDto(inventory)).collect(Collectors.toList());
  }


  public InventoryDto findInventoryById(Long id) {
    Inventory inventory = inventoryRepository.findById(id).orElseThrow(
        () -> new AddonNotFoundException("Inventory not found with id: " + id));
    return mapToDto(inventory);
  }

  public InventoryDto createInventory(InventoryDto inventoryDto) {

    Long branch_id = inventoryDto.getBranch().getId();
    Branch branch = branchRepository.findById(branch_id).orElseThrow(
        () -> new EmployeeNotFoundException("Branch not found with id: " + branch_id));

    Inventory inventory = new Inventory();

    inventory.setAriel(inventoryDto.getAriel());
    inventory.setDowny(inventoryDto.getDowny());
    inventory.setZonrox(inventoryDto.getZonrox());
    inventory.setBranch(branch);

    Inventory savedInventory = inventoryRepository.save(inventory);

    return mapToDto(savedInventory);
  }

  public InventoryDto updateInventory(InventoryDto inventoryDto, Long id) {
    Long branch_id = inventoryDto.getBranch().getId();
    Branch branch = branchRepository.findById(branch_id).orElseThrow(
        () -> new EmployeeNotFoundException("Branch not found with id: " + branch_id));

    Inventory inventory = inventoryRepository.findById(id).orElseThrow(
        () -> new AddonNotFoundException("Inventory not found with id: " + id));

    inventory.setAriel(inventoryDto.getAriel());
    inventory.setDowny(inventoryDto.getDowny());
    inventory.setZonrox(inventoryDto.getZonrox());
    inventory.setBranch(branch);

    Inventory updatedInventory = inventoryRepository.save(inventory);

    return mapToDto(updatedInventory);
  }

  public void deleteInventory(Long id) {
    Inventory inventory = inventoryRepository.findById(id).orElseThrow(
        () -> new AddonNotFoundException("Inventory not found with id: " + id));

    inventoryRepository.delete(inventory);
  }

  // >>>>>>>>  Mappers
  public static Inventory mapToEntity(InventoryDto inventory) {
    Inventory inventoryDto = Inventory.builder()
        .id(inventory.getId())
        .ariel(inventory.getAriel())
        .downy(inventory.getDowny())
        .zonrox(inventory.getZonrox())
        .branch(inventory.getBranch())
        .build();
    return inventoryDto;
  }

  public static InventoryDto mapToDto(Inventory inventory) {
    InventoryDto inventoryDto = InventoryDto.builder()
        .id(inventory.getId())
        .ariel(inventory.getAriel())
        .downy(inventory.getDowny())
        .zonrox(inventory.getZonrox())
        .branch(inventory.getBranch())
        .build();
    return inventoryDto;
  }
}
