package com.danbear.fairyflora.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

  @Service
  public class ServiceService {

    private final ServiceRepository serviceRepository;

    ServiceService(ServiceRepository serviceRepository) {
      this.serviceRepository = serviceRepository;
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
  }





