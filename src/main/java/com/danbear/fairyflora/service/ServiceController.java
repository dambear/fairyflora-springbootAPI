package com.danbear.fairyflora.service;

import com.danbear.fairyflora.addon.Addon;
import com.danbear.fairyflora.addon.AddonService;
import com.danbear.fairyflora.service.item.Item;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/services")
public class ServiceController {

  private final ServiceService serviceService;

  public ServiceController(ServiceService serviceService) {
    this.serviceService = serviceService;
  }

  @GetMapping
  public ResponseEntity<List<ServiceT>> getAllServices() {
    List<ServiceT> services = serviceService.findAllServices();
    return new ResponseEntity<>(services, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ServiceT> getServiceById(@PathVariable Long id) {
    Optional<ServiceT> addon = serviceService.findServiceById(id);
    return addon.map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<ServiceT> createService(@RequestBody ServiceT service) {
    ServiceT createdAddon = serviceService.createService(service);
    return new ResponseEntity<>(createdAddon, HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Optional<ServiceT>> updateService(@PathVariable Long id, @RequestBody ServiceT newService) {
    try {
      // Update the branch and return the updated entity
      serviceService.updateService(newService, id);
      Optional<ServiceT> updatedService = serviceService.findServiceById(id); // Retrieve the updated branch
      return ResponseEntity.ok(updatedService);
    } catch (EntityNotFoundException e) {
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteService(@PathVariable Long id) {
    try {
      serviceService.deleteService(id);
      return ResponseEntity.noContent().build(); // 204 No Content
    } catch (EntityNotFoundException e) {
      return ResponseEntity.notFound().build();
    }
  }


  @PutMapping("/{serviceId}/items/{itemId}")
  public ResponseEntity<ServiceT> assignItemToService(
      @PathVariable Long serviceId,
      @PathVariable Long itemId
  ) {
    ServiceT updatedService = serviceService.assignItemToService(serviceId, itemId);
    return ResponseEntity.ok(updatedService);
  }
}