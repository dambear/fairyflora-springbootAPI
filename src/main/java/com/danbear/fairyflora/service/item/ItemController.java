package com.danbear.fairyflora.service.item;

import com.danbear.fairyflora.addon.dto.AddonDto;
import com.danbear.fairyflora.exception.StatusObject;
import com.danbear.fairyflora.service.item.dto.ItemDto;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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
  public ResponseEntity<List<ItemDto>> getAllItems() {
    List<ItemDto> items = itemService.findAllItems();
    return new ResponseEntity<>(items, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ItemDto> getItemById(@PathVariable Long id) {
    return ResponseEntity.ok(itemService.findItemById(id));
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<ItemDto> createItem(@Valid @RequestBody ItemDto itemDto) {
    return new ResponseEntity<>(itemService.createItem(itemDto), HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<ItemDto> updateItem(
      @Valid
      @RequestBody ItemDto itemDto,
      @PathVariable("id") Long id)
  {
    ItemDto response = itemService.updateItem(itemDto, id);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<StatusObject> deleteItem(
      @PathVariable("id") Long id)
  {
    itemService.deleteItem(id);

    // Display Status and code
    StatusObject statusObject = new StatusObject();
    statusObject.setStatusCode(HttpStatus.OK.value());
    statusObject.setMessage("Addon deleted with id: " + id );
    statusObject.setTimestamp(new Date());

    return new ResponseEntity<>(statusObject, HttpStatus.OK);
  }


}