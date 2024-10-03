package com.danbear.fairyflora.service.servicelist;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

    import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/serverlists")
public class ServiceListController {

  private final ServiceListService serviceListService;

  public ServiceListController(ServiceListService serviceListService) {
    this.serviceListService = serviceListService;
  }

  @GetMapping
  public ResponseEntity<List<ServiceList>> getAllServiceLists() {
    List<ServiceList> serviceLists = serviceListService.findAllServiceLists();
    return new ResponseEntity<>(serviceLists, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ServiceList> getServiceListById(@PathVariable Long id) {
    Optional<ServiceList> addon = serviceListService.findServiceListById(id);
    return addon.map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<ServiceList> createServiceList(@RequestBody ServiceList serviceList) {
    ServiceList createdAddon = serviceListService.createServiceList(serviceList);
    return new ResponseEntity<>(createdAddon, HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Optional<ServiceList>> updateServiceList(@PathVariable Long id, @RequestBody ServiceList newServiceList) {
    try {
      // Update the branch and return the updated entity
      serviceListService.updateServiceList(newServiceList, id);
      Optional<ServiceList> updatedAddon = serviceListService.findServiceListById(id); // Retrieve the updated branch
      return ResponseEntity.ok(updatedAddon);
    } catch (EntityNotFoundException e) {
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteServiceList(@PathVariable Long id) {
    try {
      serviceListService.deleteServiceList(id);
      return ResponseEntity.noContent().build(); // 204 No Content
    } catch (EntityNotFoundException e) {
      return ResponseEntity.notFound().build();
    }
  }

  @PutMapping("/{serviceListId}/services/{serviceId}")
  public ResponseEntity<ServiceList> addServiceToServiceList(@PathVariable Long serviceListId, @PathVariable Long serviceId) {
    ServiceList updatedServiceList = serviceListService.addServiceToServiceList(serviceListId, serviceId);
    return ResponseEntity.ok(updatedServiceList);
  }

}