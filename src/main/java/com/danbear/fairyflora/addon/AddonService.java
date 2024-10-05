package com.danbear.fairyflora.addon;


import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddonService {

  private final AddonRepository addonRepository;

  AddonService(AddonRepository addonRepository) {
    this.addonRepository = addonRepository;
  }

  public List<Addon> findAllAddons() {
    return addonRepository.findAll();
  }

  public Optional<Addon> findAddonById(Long id) {
    return addonRepository.findById(id);
  }

  public Addon createAddon(Addon addon) {
    return addonRepository.save(addon);
  }

  @Transactional
  public void updateAddon(Addon newAddon, Long id) {
    Optional<Addon> existingAddonOpt =  addonRepository.findById(id);
    if(existingAddonOpt.isPresent()) {
      Addon existingAddon = existingAddonOpt.get();

      // Update fields of the existing branch
      existingAddon.setItemName(newAddon.getItemName());
      existingAddon.setPrice(newAddon.getPrice());

      // Save the updated entity
      addonRepository.save(existingAddon);
    }else {
      throw new EntityNotFoundException("Addon not found with id: " + id);
    }
  }


  public void deleteAddon(Long id) {
    addonRepository.deleteById(id);
  }
}


