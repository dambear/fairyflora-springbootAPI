package com.danbear.fairyflora.addon;


import com.danbear.fairyflora.addon.dto.AddonDto;

import java.util.List;

public interface AddonService {
  List<AddonDto> findAllAddons();
  AddonDto findAddonById(Long id);
  AddonDto createAddon(AddonDto addonDto);
  AddonDto updateAddon(AddonDto addonDto, Long id);
  void deleteAddon(Long id);
}


