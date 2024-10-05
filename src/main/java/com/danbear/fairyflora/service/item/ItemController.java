package com.danbear.fairyflora.service.item;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

    import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/items")
public class ItemController {

  private final ItemService itemService;

  public ItemController(ItemService itemService) {
    this.itemService = itemService;
  }

  @GetMapping
  public ResponseEntity<List<Item>> getAllServiceLists() {
    List<Item> items = itemService.findAllItems();
    return new ResponseEntity<>(items, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Item> getServiceListById(@PathVariable Long id) {
    Optional<Item> addon = itemService.findItemById(id);
    return addon.map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<Item> createServiceList(@RequestBody Item item) {
    Item createdAddon = itemService.createItem(item);
    return new ResponseEntity<>(createdAddon, HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Optional<Item>> updateServiceList(@PathVariable Long id, @RequestBody Item newItem) {
    try {
      // Update the branch and return the updated entity
      itemService.updateItem(newItem, id);
      Optional<Item> updatedAddon = itemService.findItemById(id); // Retrieve the updated branch
      return ResponseEntity.ok(updatedAddon);
    } catch (EntityNotFoundException e) {
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteServiceList(@PathVariable Long id) {
    try {
      itemService.deleteServiceList(id);
      return ResponseEntity.noContent().build(); // 204 No Content
    } catch (EntityNotFoundException e) {
      return ResponseEntity.notFound().build();
    }
  }


}