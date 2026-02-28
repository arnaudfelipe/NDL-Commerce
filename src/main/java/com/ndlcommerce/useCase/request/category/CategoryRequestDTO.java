package com.ndlcommerce.useCase.request.category;

import java.util.UUID;

public class CategoryRequestDTO {

  private String name;

  private UUID parentId;

  public CategoryRequestDTO(String name, UUID parentId) {
    this.name = name;
    this.parentId = parentId;
  }

  public String getName() {
    return name;
  }

  public UUID getParentId() {
    return parentId;
  }

}
