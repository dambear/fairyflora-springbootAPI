package com.danbear.fairyflora.service.item;

import com.danbear.fairyflora.addon.AddonNotFoundException;
import com.danbear.fairyflora.service.item.dto.ItemDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService {

  private final ItemRepository itemRepository;

  public ItemServiceImpl(ItemRepository itemRepository) {
    this.itemRepository = itemRepository;
  }


  public List<ItemDto> findAllItems() {
    List<Item> items = itemRepository.findAll();
    return items.stream().map((item) -> mapToDto(item)).collect(Collectors.toList());
  }

  public ItemDto findItemById(Long id) {
    Item item = itemRepository.findById(id).orElseThrow(
        () -> new AddonNotFoundException("Item not found with id: " + id));
    return mapToDto(item);
  }

  public ItemDto createItem(ItemDto itemDto) {
    Item item = new Item();

    item.setItemName(itemDto.getItemName());
    item.setPrice(itemDto.getPrice());

    Item savedItem = itemRepository.save(item);

    return mapToDto(savedItem);
  }

  public ItemDto updateItem(ItemDto itemDto, Long id) {
    Item item = itemRepository.findById(id).orElseThrow(
        () -> new AddonNotFoundException("Item not found with id: " + id));

    item.setItemName(itemDto.getItemName());
    item.setPrice(itemDto.getPrice());

    Item updatedItem = itemRepository.save(item);

    return mapToDto(updatedItem);
  }

  @Override
  public void deleteItem(Long id) {
    Item item = itemRepository.findById(id).orElseThrow(
        () -> new AddonNotFoundException("Item not found with id: " + id));

    itemRepository.delete(item);
  }


  // >>>>>>>>  Mappers
  public static Item mapToEntity(ItemDto item) {
    Item itemDto = Item.builder()
        .id(item.getId())
        .itemName(item.getItemName())
        .price(item.getPrice())
        .build();
    return itemDto;
  }

  public static ItemDto mapToDto(Item item) {
    ItemDto itemDto = ItemDto.builder()
        .id(item.getId())
        .itemName(item.getItemName())
        .price(item.getPrice())
        .build();
    return itemDto;
  }

}
