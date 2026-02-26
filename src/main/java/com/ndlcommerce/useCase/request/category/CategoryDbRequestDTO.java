package com.ndlcommerce.useCase.request.category;

import java.util.UUID;
import lombok.Getter;

public class CategoryDbRequestDTO {

  @Getter private String name;

  @Getter private String description;

  @Getter private UUID parentId;

  public CategoryDbRequestDTO(String name, String description, UUID parentId) {
    this.name = name;
    this.description = description;
    this.parentId = parentId;
  }
}
