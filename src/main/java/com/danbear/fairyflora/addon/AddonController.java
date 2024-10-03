package com.danbear.fairyflora.addon;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/addons")
public class AddonController {

  private final AddonService addonService;

  public AddonController(AddonService addonService) {
    this.addonService = addonService;
  }

  @GetMapping
  public ResponseEntity<List<Addon>> getAllAddons() {
    List<Addon> addons = addonService.findAllAddons();
    return new ResponseEntity<>(addons, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Addon> getAddonById(@PathVariable Long id) {
    Optional<Addon> addon = addonService.findAddonById(id);
    return addon.map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<Addon> createAddon(@RequestBody Addon addon) {
    Addon createdAddon = addonService.createAddon(addon);
    return new ResponseEntity<>(createdAddon, HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Optional<Addon>> updateAddon(@PathVariable Long id, @RequestBody Addon newAddon) {
    try {
      // Update the branch and return the updated entity
      addonService.updateAddon(newAddon, id);
      Optional<Addon> updatedAddon = addonService.findAddonById(id); // Retrieve the updated branch
      return ResponseEntity.ok(updatedAddon);
    } catch (EntityNotFoundException e) {
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteAddon(@PathVariable Long id) {
    try {
      addonService.deleteAddon(id);
      return ResponseEntity.noContent().build(); // 204 No Content
    } catch (EntityNotFoundException e) {
      return ResponseEntity.notFound().build();
    }
  }
}