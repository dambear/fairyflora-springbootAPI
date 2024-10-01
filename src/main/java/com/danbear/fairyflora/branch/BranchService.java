package com.danbear.fairyflora.branch;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

  @Service
  public class BranchService {

    private final BranchRepository branchRepository;

    BranchService(BranchRepository branchRepository) {
      this.branchRepository = branchRepository;
    }

    public List<Branch> findAllBranches() {
      return branchRepository.findAll();
    }

    public Optional<Branch> findBranchById(Long id) {
      return branchRepository.findById(id);
    }

    public Branch createBranch(Branch branch) {
      return branchRepository.save(branch);
    }

    @Transactional
    public void updateBranch(Branch newBranch, Long id) {
      Optional<Branch> existingBranchOpt =  branchRepository.findById(id);
      if(existingBranchOpt.isPresent()) {
        Branch existingBranch = existingBranchOpt.get();

        // Update fields of the existing branch
        existingBranch.setBarangay(newBranch.getBarangay());
        existingBranch.setMunicipality(newBranch.getMunicipality());
        existingBranch.setProvince(newBranch.getProvince());
        existingBranch.setOpeningTime(newBranch.getOpeningTime());
        existingBranch.setClosingTime(newBranch.getClosingTime());
        existingBranch.setEmailAddress(newBranch.getEmailAddress());
        existingBranch.setDateEstablished(newBranch.getDateEstablished());

        // Save the updated entity
        branchRepository.save(existingBranch);
      }else {
        throw new EntityNotFoundException("Branch not found with id: " + id);
      }
    }


    public void deleteBranch(Long id) {
      branchRepository.deleteById(id);
    }
  }


