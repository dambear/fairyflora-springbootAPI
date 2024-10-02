package com.danbear.fairyflora.inventory;

import com.danbear.fairyflora.branch.Branch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

}
