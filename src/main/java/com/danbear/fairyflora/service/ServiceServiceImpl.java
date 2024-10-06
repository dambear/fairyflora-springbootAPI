package com.danbear.fairyflora.service;

import com.danbear.fairyflora.addon.AddonNotFoundException;
import com.danbear.fairyflora.service.dto.ServiceTDto;
import com.danbear.fairyflora.service.item.Item;
import com.danbear.fairyflora.service.item.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ServiceServiceImpl implements ServiceService {

  private final ServiceRepository serviceRepository;
  private final ItemRepository itemRepository;

  public ServiceServiceImpl(ServiceRepository serviceRepository, ItemRepository itemRepository) {
    this.serviceRepository = serviceRepository;
    this.itemRepository = itemRepository;
  }

  public List<ServiceTDto> findAllServices() {
    List<ServiceT> services = serviceRepository.findAll();
    return services.stream().map((service) -> mapToDto(service)).collect(Collectors.toList());
  }


  public ServiceTDto findServiceById(Long id) {
    ServiceT service = serviceRepository.findById(id).orElseThrow(
        () -> new AddonNotFoundException("Service not found with id: " + id));
    return mapToDto(service);
  }

  public ServiceTDto createService(ServiceTDto serviceTDto) {
    ServiceT service = new ServiceT();

    service.setServiceName(serviceTDto.getServiceName());
    service.setServiceCode(serviceTDto.getServiceCode());
    service.setTotalPrice(serviceTDto.getTotalPrice());
    service.setDescription(serviceTDto.getDescription());
    service.setItems(serviceTDto.getItems());

    service.calculateTotalPrice();

    ServiceT savedService = serviceRepository.save(service);

    return mapToDto(savedService);
  }

  public ServiceTDto updateService(ServiceTDto serviceTDto, Long id) {
    ServiceT service = serviceRepository.findById(id).orElseThrow(
        () -> new AddonNotFoundException("Service not found with id: " + id));

    service.setServiceName(serviceTDto.getServiceName());
    service.setServiceCode(serviceTDto.getServiceCode());
    service.setTotalPrice(serviceTDto.getTotalPrice());
    service.setDescription(serviceTDto.getDescription());
    service.setItems(serviceTDto.getItems());

    service.calculateTotalPrice();

    ServiceT updatedService = serviceRepository.save(service);

    return mapToDto(updatedService);
  }

  public void deleteService(Long id) {
    ServiceT service = serviceRepository.findById(id).orElseThrow(
        () -> new AddonNotFoundException("Service not found with id: " + id));

    serviceRepository.delete(service);
  }

  public ServiceTDto assignItemToService(Long service_id, Long item_id) {
    Set<Item> itemSet;

    ServiceT service = serviceRepository.findById(service_id).orElseThrow(
        () -> new AddonNotFoundException("Service not found with id: " + service_id));

    Item item = itemRepository.findById(item_id).orElseThrow(
        () -> new AddonNotFoundException("Item not found with id: " + item_id));

    itemSet = service.getItems();
    itemSet.add(item);

    service.setItems(itemSet);

    service.calculateTotalPrice();

    ServiceT assign = serviceRepository.save(service);

    return mapToDto(assign);
  }

  // >>>>>>>>  Mappers
  public static ServiceT mapToEntity(ServiceTDto service) {
    ServiceT serviceTDto = ServiceT.builder()
        .id(service.getId())
        .serviceName(service.getServiceName())
        .serviceCode(service.getServiceCode())
        .totalPrice(service.getTotalPrice())
        .description(service.getDescription())
        .items(service.getItems())
        .build();
    return serviceTDto;
  }

  public static ServiceTDto mapToDto(ServiceT service) {
    ServiceTDto serviceTDto = ServiceTDto.builder()
        .id(service.getId())
        .serviceName(service.getServiceName())
        .serviceCode(service.getServiceCode())
        .totalPrice(service.getTotalPrice())
        .description(service.getDescription())
        .items(service.getItems())
        .build();
    return serviceTDto;
  }
}
