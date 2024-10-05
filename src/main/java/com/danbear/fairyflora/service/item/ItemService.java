package com.danbear.fairyflora.service.item;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

  private final ItemRepository itemRepository;

  ItemService(ItemRepository itemRepository) {
    this.itemRepository = itemRepository;
  }

  public List<Item> findAllItems() {
    return itemRepository.findAll();
  }

  public Optional<Item> findItemById(Long id) {
    return itemRepository.findById(id);
  }

  public Item createItem(Item item) {
    return itemRepository.save(item);
  }

  @Transactional
  public void updateItem(Item newItem, Long id) {
    Optional<Item> existingItemOpt =  itemRepository.findById(id);
    if(existingItemOpt.isPresent()) {
      Item existingItem = existingItemOpt.get();

      // Update fields of the existing branch
      existingItem.setItemName(newItem.getItemName());
      existingItem.setPrice(newItem.getPrice());

      // Save the updated entity
      itemRepository.save(existingItem);
    }else {
      throw new EntityNotFoundException("ServiceList not found with id: " + id);
    }
  }


  public void deleteServiceList(Long id) {
    itemRepository.deleteById(id);
  }



}


