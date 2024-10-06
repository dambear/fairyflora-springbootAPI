package com.danbear.fairyflora.service.item;

import com.danbear.fairyflora.addon.dto.AddonDto;
import com.danbear.fairyflora.service.item.dto.ItemDto;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public interface ItemService {
  List<ItemDto> findAllItems();
  ItemDto findItemById(Long id);
  ItemDto createItem(ItemDto itemDto);
  ItemDto updateItem(ItemDto itemDto, Long id);
  void deleteItem(Long id);
}


