package com.danbear.fairyflora.service;

import com.danbear.fairyflora.service.item.Item;
import com.danbear.fairyflora.service.item.ItemRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
  public class ServiceService {

    private final ServiceRepository serviceRepository;
    private final ItemRepository itemRepository;

    ServiceService(ServiceRepository serviceRepository, ItemRepository itemRepository) {
      this.serviceRepository = serviceRepository;
      this.itemRepository = itemRepository;
    }

    public List<ServiceT> findAllServices() {
      return serviceRepository.findAll();
    }

    public Optional<ServiceT> findServiceById(Long id) {
      return serviceRepository.findById(id);
    }

    public ServiceT createService(ServiceT addon) {
      return serviceRepository.save(addon);
    }

    @Transactional
    public void updateService(ServiceT newService, Long id) {
      Optional<ServiceT> existingServiceOpt =  serviceRepository.findById(id);
      if(existingServiceOpt.isPresent()) {
        ServiceT existingService = existingServiceOpt.get();

        // Update fields of the existing branch
        existingService.setServiceName(newService.getServiceName());
        existingService.setServiceCode(newService.getServiceCode());
        existingService.setDescription(newService.getDescription());

        // Recalculate the total price based on assigned services
        existingService.calculateTotalPrice();

        // Save the updated entity
        serviceRepository.save(existingService);
      }else {
        throw new EntityNotFoundException("Service not found with id: " + id);
      }
    }


    public void deleteService(Long id) {
      serviceRepository.deleteById(id);
    }


    public ServiceT assignItemToService(Long service_id, Long item_id) {
      Set<Item> itemSet;

      ServiceT service =  serviceRepository.findById(service_id).get();
      Item item = itemRepository.findById(item_id).get();

      itemSet = service.getItems();
      itemSet.add(item);

      service.setItems(itemSet);
      service.calculateTotalPrice();

      return serviceRepository.save(service);
    }

//  @Transactional
//  public ServiceT assignItemToService(Long service_id, Long item_id) {
//    Set<Item> itemSet;
//    Set<ServiceT> serviceTSet;
//    ServiceT service =  serviceRepository.findById(service_id).get();
//    Item item = itemRepository.findById(item_id).get();
//
//    itemSet = service.getAssignedItems();
//    itemSet.add(item);
//
//    serviceTSet = item.getServices();
//    serviceTSet.add(service);
//
//    item.setServices(serviceTSet);
//    service.setAssignedItems(itemSet);
//    itemRepository.save(item);
//    return serviceRepository.save(service);
//  }


//  public ServiceT assignItemToService(Long service_id, Long item_id) {
//    ServiceT service = serviceRepository.findById(service_id)
//        .orElseThrow(() -> new EntityNotFoundException("Service not found with id: " + service_id));
//    Item item = itemRepository.findById(item_id)
//        .orElseThrow(() -> new EntityNotFoundException("Item not found with id: " + item_id));
//
//
//    service.getItems().add(item);
//
//
//    return serviceRepository.save(service);
//  }

  }




