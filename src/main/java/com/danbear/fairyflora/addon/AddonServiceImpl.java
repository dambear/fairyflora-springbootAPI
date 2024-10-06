package com.danbear.fairyflora.addon;

import com.danbear.fairyflora.addon.dto.AddonDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddonServiceImpl implements AddonService {

  private final AddonRepository addonRepository;

  public AddonServiceImpl(AddonRepository addonRepository) {
    this.addonRepository = addonRepository;
  }

  public List<AddonDto> findAllAddons() {
    List<Addon> addons = addonRepository.findAll();
    return addons.stream().map((addon) -> mapToDto(addon)).collect(Collectors.toList());
  }


  public AddonDto findAddonById(Long id) {
    Addon addon = addonRepository.findById(id).orElseThrow(
        () -> new AddonNotFoundException("Addon not found with id: " + id));
    return mapToDto(addon);
  }

  public AddonDto createAddon(AddonDto addonDto) {
    Addon addon = new Addon();

    addonDto.setQuantity(0L);
    addonDto.setTotalPrice(0L);

    addon.setItemName(addonDto.getItemName());
    addon.setPrice(addonDto.getPrice());
    addon.setQuantity(addonDto.getQuantity());
    addon.setTotalPrice(addonDto.getTotalPrice());

    Addon savedAddon = addonRepository.save(addon);

    return mapToDto(savedAddon);
  }

  public AddonDto updateAddon(AddonDto addonDto, Long id) {
    Addon addon = addonRepository.findById(id).orElseThrow(
        () -> new AddonNotFoundException("Addon not found with id: " + id));

    addon.setItemName(addonDto.getItemName());
    addon.setPrice(addonDto.getPrice());
    addon.setQuantity(addonDto.getQuantity());
    addon.setTotalPrice(addonDto.getTotalPrice());

    Addon updatedAddon = addonRepository.save(addon);

    return mapToDto(updatedAddon);
  }

  public void deleteAddon(Long id) {
    Addon addon = addonRepository.findById(id).orElseThrow(
        () -> new AddonNotFoundException("Addon not found with id: " + id));

    addonRepository.delete(addon);
  }

  // >>>>>>>>  Mappers
  public static Addon mapToEntity(AddonDto addon) {
    Addon addonDto = Addon.builder()
        .id(addon.getId())
        .itemName(addon.getItemName())
        .price(addon.getPrice())
        .quantity(addon.getQuantity())
        .totalPrice(addon.getTotalPrice())
        .build();
    return addonDto;
  }

  public static AddonDto mapToDto(Addon addon) {
    AddonDto addonDto = AddonDto.builder()
        .id(addon.getId())
        .itemName(addon.getItemName())
        .price(addon.getPrice())
        .quantity(addon.getQuantity())
        .totalPrice(addon.getTotalPrice())
        .build();
    return addonDto;
  }

}
