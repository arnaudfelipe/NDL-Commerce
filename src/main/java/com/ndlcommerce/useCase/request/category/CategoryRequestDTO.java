package com.ndlcommerce.useCase.request.category;

import java.util.UUID;

public class CategoryRequestDTO {

  private String name;

  private String description;
  private UUID parentId;

  public CategoryRequestDTO(String name, String description, UUID parentId) {
    this.name = name;
    this.description = description;
    this.parentId = parentId;
  }

  public String getName() {
    return name;
  }

  public UUID getParentId() {
    return parentId;
  }

  public String getDescription() {
    return description;
  }
}
