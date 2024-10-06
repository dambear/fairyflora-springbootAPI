package com.danbear.fairyflora.addon;

import com.danbear.fairyflora.addon.dto.AddonDto;
import com.danbear.fairyflora.exception.StatusObject;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/api/addons")
public class AddonController {

  private final AddonService addonService;

  public AddonController(AddonService addonService) {
    this.addonService = addonService;
  }


  @GetMapping
  public ResponseEntity<List<AddonDto>> getAllAddons() {
    List<AddonDto> addons = addonService.findAllAddons();
    return new ResponseEntity<>(addons, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<AddonDto> getAddonById(@PathVariable Long id) {
    return ResponseEntity.ok(addonService.findAddonById(id));
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<AddonDto> createAddon(@Valid @RequestBody AddonDto addonDto) {
    return new ResponseEntity<>(addonService.createAddon(addonDto), HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<AddonDto> updateAddon(
      @Valid
      @RequestBody AddonDto addonDto,
      @PathVariable("id") Long id)
  {
    AddonDto response = addonService.updateAddon(addonDto, id);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<StatusObject> deleteAddon(
      @PathVariable("id") Long id)
  {
    addonService.deleteAddon(id);

    // Display Status and code
    StatusObject statusObject = new StatusObject();
    statusObject.setStatusCode(HttpStatus.OK.value());
    statusObject.setMessage("Addon deleted with id: " + id );
    statusObject.setTimestamp(new Date());

    return new ResponseEntity<>(statusObject, HttpStatus.OK);
  }

}