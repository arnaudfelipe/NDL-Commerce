package com.ndlcommerce.useCase.request.product;

public class ProductRequestDTO {
  private String name;

  private String description;

  public ProductRequestDTO(String name, String description) {
    this.name = name;
    this.description = description;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }
}
