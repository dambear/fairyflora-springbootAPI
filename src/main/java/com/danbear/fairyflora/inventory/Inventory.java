package com.danbear.fairyflora.inventory;

import com.danbear.fairyflora.branch.Branch;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "inventory")
public class Inventory {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Long ariel;

  private Long downy;

  private Long zonrox;

  @OneToOne
  @JoinColumn( referencedColumnName = "id")
  @JsonIgnoreProperties({"inventory","employees"}) // Use a foreign key
  private Branch branch;

}
