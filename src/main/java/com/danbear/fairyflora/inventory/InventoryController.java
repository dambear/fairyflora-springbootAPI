package com.danbear.fairyflora.inventory;

import com.danbear.fairyflora.addon.dto.AddonDto;
import com.danbear.fairyflora.branch.Branch;
import com.danbear.fairyflora.exception.StatusObject;
import com.danbear.fairyflora.inventory.Inventory;
import com.danbear.fairyflora.inventory.InventoryService;
import com.danbear.fairyflora.inventory.dto.InventoryDto;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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
  public ResponseEntity<List<InventoryDto>> getAllInventories() {
    List<InventoryDto> inventories = inventoryService.findAllInventories();
    return new ResponseEntity<>(inventories, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<InventoryDto> getInventoryById(@PathVariable Long id) {
    return ResponseEntity.ok(inventoryService.findInventoryById(id));
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<InventoryDto> createAddon(@Valid @RequestBody InventoryDto inventoryDto) {
    return new ResponseEntity<>(inventoryService.createInventory(inventoryDto), HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<InventoryDto> updateInventory(
      @Valid
      @RequestBody InventoryDto inventoryDto,
      @PathVariable("id") Long id)
  {
    InventoryDto response = inventoryService.updateInventory(inventoryDto, id);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<StatusObject> deleteInventory(
      @PathVariable("id") Long id)
  {
    inventoryService.deleteInventory(id);

    // Display Status and code
    StatusObject statusObject = new StatusObject();
    statusObject.setStatusCode(HttpStatus.OK.value());
    statusObject.setMessage("Inventory deleted with id: " + id );
    statusObject.setTimestamp(new Date());

    return new ResponseEntity<>(statusObject, HttpStatus.OK);
  }

}