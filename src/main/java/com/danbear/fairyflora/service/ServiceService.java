package com.danbear.fairyflora.service;

import com.danbear.fairyflora.addon.dto.AddonDto;
import com.danbear.fairyflora.service.dto.ServiceTDto;
import com.danbear.fairyflora.service.item.Item;
import com.danbear.fairyflora.service.item.ItemRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public interface ServiceService {
  List<ServiceTDto> findAllServices();
  ServiceTDto findServiceById(Long id);
  ServiceTDto createService(ServiceTDto serviceTDto);
  ServiceTDto updateService(ServiceTDto serviceTDto, Long id);
  void deleteService(Long id);
  ServiceTDto assignItemToService(Long service_id, Long item_id);
}




