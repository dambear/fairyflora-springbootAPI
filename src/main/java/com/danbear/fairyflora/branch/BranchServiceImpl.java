package com.danbear.fairyflora.branch;

import com.danbear.fairyflora.addon.Addon;
import com.danbear.fairyflora.addon.AddonNotFoundException;
import com.danbear.fairyflora.addon.dto.AddonDto;
import com.danbear.fairyflora.branch.dto.BranchDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BranchServiceImpl implements BranchService {

  private final BranchRepository branchRepository;

  public BranchServiceImpl(BranchRepository branchRepository) {
    this.branchRepository = branchRepository;
  }


  public List<BranchDto> findAllBranches() {
    List<Branch> branches = branchRepository.findAll();
    return branches.stream().map((branch) -> mapToDto(branch)).collect(Collectors.toList());
  }

  public BranchDto findBranchById(Long id) {
    Branch branch = branchRepository.findById(id).orElseThrow(
        () -> new BranchNotFoundException("Branch not found with id: " + id));
    return mapToDto(branch);
  }


  public BranchDto createBranch(BranchDto branchDto) {
    Branch branch = new Branch();

    branch.setBarangay(branchDto.getBarangay());
    branch.setMunicipality(branchDto.getMunicipality());
    branch.setProvince(branchDto.getProvince());
    branch.setOpeningTime(branchDto.getOpeningTime());
    branch.setClosingTime(branchDto.getClosingTime());
    branch.setEmailAddress(branchDto.getEmailAddress());
    branch.setDateEstablished(branchDto.getDateEstablished());
    branch.setEmployees(branchDto.getEmployees());
    branch.setInventory(branchDto.getInventory());

    Branch savedBranch = branchRepository.save(branch);

    return mapToDto(savedBranch);
  }


  public BranchDto updateBranch(BranchDto branchDto, Long id) {
    Branch branch = branchRepository.findById(id).orElseThrow(
        () -> new BranchNotFoundException("Branch not found with id: " + id));

    branch.setBarangay(branchDto.getBarangay());
    branch.setMunicipality(branchDto.getMunicipality());
    branch.setProvince(branchDto.getProvince());
    branch.setOpeningTime(branchDto.getOpeningTime());
    branch.setClosingTime(branchDto.getClosingTime());
    branch.setEmailAddress(branchDto.getEmailAddress());
    branch.setDateEstablished(branchDto.getDateEstablished());
    branch.setEmployees(branchDto.getEmployees());
    branch.setInventory(branchDto.getInventory());


    Branch updatedBranch = branchRepository.save(branch);

    return mapToDto(updatedBranch);
  }

  public void deleteBranch(Long id) {
    Branch branch = branchRepository.findById(id).orElseThrow(
        () -> new BranchNotFoundException("Branch not found with id: " + id));

    branchRepository.delete(branch);
  }


  // >>>>>>>>  Mappers
  public static Branch mapToEntity(BranchDto branch) {
    Branch branchDto = Branch.builder()
        .id(branch.getId())
        .barangay(branch.getBarangay())
        .municipality(branch.getMunicipality())
        .province(branch.getProvince())
        .openingTime(branch.getOpeningTime())
        .closingTime(branch.getClosingTime())
        .emailAddress(branch.getEmailAddress())
        .dateEstablished(branch.getDateEstablished())
        .employees(branch.getEmployees())
        .inventory(branch.getInventory())
        .build();
    return branchDto;
  }

  public static BranchDto mapToDto(Branch branch) {
    BranchDto branchDto = BranchDto.builder()
        .id(branch.getId())
        .barangay(branch.getBarangay())
        .municipality(branch.getMunicipality())
        .province(branch.getProvince())
        .openingTime(branch.getOpeningTime())
        .closingTime(branch.getClosingTime())
        .emailAddress(branch.getEmailAddress())
        .dateEstablished(branch.getDateEstablished())
        .employees(branch.getEmployees())
        .inventory(branch.getInventory())
        .build();
    return branchDto;
  }


}
