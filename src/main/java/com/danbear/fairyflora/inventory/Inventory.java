package com.danbear.fairyflora.inventory;

import com.danbear.fairyflora.branch.Branch;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@Table(name = "inventory")
public class Inventory {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotNull
  private Long ariel;

  @NotNull
  private Long downy;

  @NotNull
  private Long zonrox;

  @OneToOne
  @JoinColumn( referencedColumnName = "id") // Use a foreign key
  private Branch branch;

}
