package com.ndlcommerce.useCase.request.category;

import java.util.UUID;
import lombok.Setter;

public class CategoryResponseDTO {

  private final UUID id;
  private final String name;
  private final UUID parentId;
  @Setter private String createdAt;

  public CategoryResponseDTO(UUID id, String name, UUID parentId, String createdAt) {
    this.id = id;
    this.name = name;
    this.parentId = parentId;
    this.createdAt = createdAt;
  }

  public UUID getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public UUID getParentId() {
    return parentId;
  }

  public String getCreatedAt() {
    return createdAt;
  }
}
