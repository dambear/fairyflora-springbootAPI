package com.danbear.fairyflora.branch;


import com.danbear.fairyflora.branch.dto.BranchDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface BranchService {
  List<BranchDto> findAllBranches();
  BranchDto findBranchById(Long id);
  BranchDto createBranch(BranchDto branchDto);
  BranchDto updateBranch(BranchDto branchDto, Long id);
  void deleteBranch(Long id);

}


