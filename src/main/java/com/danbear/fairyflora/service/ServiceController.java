package com.danbear.fairyflora.service;

import com.danbear.fairyflora.addon.Addon;
import com.danbear.fairyflora.addon.AddonService;
import com.danbear.fairyflora.addon.dto.AddonDto;
import com.danbear.fairyflora.exception.StatusObject;
import com.danbear.fairyflora.service.dto.ServiceTDto;
import com.danbear.fairyflora.service.item.Item;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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
  public ResponseEntity<List<ServiceTDto>> getAllServices() {
    List<ServiceTDto> services = serviceService.findAllServices();
    return new ResponseEntity<>(services, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ServiceTDto> getAddonById(@PathVariable Long id) {
    return ResponseEntity.ok(serviceService.findServiceById(id));
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<ServiceTDto> createAddon(@Valid @RequestBody ServiceTDto serviceTDto) {
    return new ResponseEntity<>(serviceService.createService(serviceTDto), HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<ServiceTDto> updateAddon(
      @Valid
      @RequestBody ServiceTDto serviceTDto,
      @PathVariable("id") Long id)
  {
    ServiceTDto response = serviceService.updateService(serviceTDto, id);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<StatusObject> deleteAddon(
      @PathVariable("id") Long id)
  {
    serviceService.deleteService(id);

    // Display Status and code
    StatusObject statusObject = new StatusObject();
    statusObject.setStatusCode(HttpStatus.OK.value());
    statusObject.setMessage("Service deleted with id: " + id );
    statusObject.setTimestamp(new Date());

    return new ResponseEntity<>(statusObject, HttpStatus.OK);
  }

  @PutMapping("/{serviceId}/items/{itemId}")
  public ResponseEntity<ServiceTDto> assignItemToService(
      @PathVariable Long serviceId,
      @PathVariable Long itemId
  ) {
    ServiceTDto updatedService = serviceService.assignItemToService(serviceId, itemId);
    return ResponseEntity.ok(updatedService);
  }


}