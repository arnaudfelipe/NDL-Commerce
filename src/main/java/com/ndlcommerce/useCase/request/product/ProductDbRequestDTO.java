package com.ndlcommerce.useCase.request.product;

import java.util.Objects;
import lombok.Getter;

public class ProductDbRequestDTO {

  @Getter private String name;
  @Getter private String description;
  @Getter private String brand;
  @Getter private boolean active;

    public ProductDbRequestDTO(String brand, String description, String name) {
        this.brand = brand;
        this.description = description;
        this.name = name;
    }

    public ProductDbRequestDTO(String name, String description, String brand, boolean active) {
    this.name = name;
    this.description = description;
      this.brand = brand;
      this.active = active;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    ProductDbRequestDTO that = (ProductDbRequestDTO) o;
    return Objects.equals(name, that.name) && Objects.equals(description, that.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, description);
  }


}
