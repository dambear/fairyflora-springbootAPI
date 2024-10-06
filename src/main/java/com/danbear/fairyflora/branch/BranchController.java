package com.danbear.fairyflora.branch;

import com.danbear.fairyflora.branch.dto.BranchDto;
import com.danbear.fairyflora.exception.StatusObject;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/branches")
public class BranchController {

  private final BranchService branchService;

  public BranchController(BranchService branchService) {
    this.branchService = branchService;
  }

  @GetMapping
  public ResponseEntity<List<BranchDto>> getAllAddons() {
    List<BranchDto> branches = branchService.findAllBranches();
    return new ResponseEntity<>(branches, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<BranchDto> getAddonById(@PathVariable Long id) {
    return ResponseEntity.ok(branchService.findBranchById(id));
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<BranchDto> createAddon(@Valid @RequestBody BranchDto branchDto) {
    return new ResponseEntity<>(branchService.createBranch(branchDto), HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<BranchDto> updateAddon(
      @Valid
      @RequestBody BranchDto branchDto,
      @PathVariable("id") Long id)
  {
    BranchDto response = branchService.updateBranch(branchDto, id);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<StatusObject> deleteAddon(
      @PathVariable("id") Long id)
  {
    branchService.deleteBranch(id);

    // Display Status and code
    StatusObject statusObject = new StatusObject();
    statusObject.setStatusCode(HttpStatus.OK.value());
    statusObject.setMessage("Branch deleted with id: " + id );
    statusObject.setTimestamp(new Date());

    return new ResponseEntity<>(statusObject, HttpStatus.OK);
  }


}