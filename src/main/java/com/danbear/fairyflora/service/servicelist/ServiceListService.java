package com.danbear.fairyflora.service.servicelist;


import com.danbear.fairyflora.service.ServiceT;
import com.danbear.fairyflora.service.ServiceService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ServiceListService {

  private final ServiceListRepository serviceListRepository;
  private final ServiceService serviceService;

  ServiceListService(ServiceListRepository serviceListRepository, ServiceService serviceService) {
    this.serviceListRepository = serviceListRepository;
    this.serviceService = serviceService;
  }

  public List<ServiceList> findAllServiceLists() {
    return serviceListRepository.findAll();
  }

  public Optional<ServiceList> findServiceListById(Long id) {
    return serviceListRepository.findById(id);
  }

  public ServiceList createServiceList(ServiceList serviceList) {
    return serviceListRepository.save(serviceList);
  }

  @Transactional
  public void updateServiceList(ServiceList newServiceList, Long id) {
    Optional<ServiceList> existingServiceListOpt =  serviceListRepository.findById(id);
    if(existingServiceListOpt.isPresent()) {
      ServiceList existingServiceList = existingServiceListOpt.get();

      // Update fields of the existing branch
      existingServiceList.setItemName(newServiceList.getItemName());
      existingServiceList.setPrice(newServiceList.getPrice());

      // Save the updated entity
      serviceListRepository.save(existingServiceList);
    }else {
      throw new EntityNotFoundException("ServiceList not found with id: " + id);
    }
  }


  public void deleteServiceList(Long id) {
    serviceListRepository.deleteById(id);
  }


  public ServiceList addServiceToServiceList(Long serviceListId, Long serviceId) {
    ServiceList serviceList = serviceListRepository.findById(serviceListId)
        .orElseThrow(() -> new RuntimeException("ServiceList not found"));

    ServiceT service = serviceService.findServiceById(serviceId)
        .orElseThrow(() -> new RuntimeException("Service not found"));

    // Add the service to the service list
    serviceList.getServices().add(service);

    return serviceListRepository.save(serviceList);
  }

  public ServiceList removeServiceFromServiceList(Long serviceListId, Long serviceId) {
    ServiceList serviceList = serviceListRepository.findById(serviceListId)
        .orElseThrow(() -> new RuntimeException("ServiceList not found"));

    ServiceT service = serviceService.findServiceById(serviceId)
        .orElseThrow(() -> new RuntimeException("Service not found"));

    // Remove the service from the service list
    if (!serviceList.getServices().remove(service)) {
      throw new RuntimeException("Service not found in the ServiceList");
    }

    return serviceListRepository.save(serviceList);
  }




}


