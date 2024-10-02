package com.danbear.fairyflora.branch;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/branches")
public class BranchController {

  private final BranchService branchService;

  public BranchController(BranchService branchService) {
    this.branchService = branchService;
  }

  @GetMapping
  public ResponseEntity<List<Branch>> getAllBranches() {
    List<Branch> branches = branchService.findAllBranches();
    return new ResponseEntity<>(branches, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Branch> getBranchById(@PathVariable Long id) {
    Optional<Branch> branch = branchService.findBranchById(id);
    return branch.map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<Branch> createBranch(@RequestBody Branch branch) {
    Branch createdBranch = branchService.createBranch(branch);
    return new ResponseEntity<>(createdBranch, HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Optional<Branch>> updateBranch(@PathVariable Long id, @RequestBody Branch newBranch) {
    try {
      // Update the branch and return the updated entity
      branchService.updateBranch(newBranch, id);
      Optional<Branch> updatedBranch = branchService.findBranchById(id); // Retrieve the updated branch
      return ResponseEntity.ok(updatedBranch);
    } catch (EntityNotFoundException e) {
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteBranch(@PathVariable Long id) {
    try {
      branchService.deleteBranch(id);
      return ResponseEntity.noContent().build(); // 204 No Content
    } catch (EntityNotFoundException e) {
      return ResponseEntity.notFound().build();
    }
  }
}