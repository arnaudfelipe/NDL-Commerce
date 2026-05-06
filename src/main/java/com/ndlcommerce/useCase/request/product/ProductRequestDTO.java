package com.ndlcommerce.useCase.request.product;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.UUID;

public class ProductRequestDTO {

  @Schema(example = "Notebook Dell Inspiron")
  @NotBlank(message = "Nome deve ter entre 3 e 200 caracteres")
  @Size(min = 3, max = 200, message = "Nome deve ter entre 3 e 200 caracteres")
  private String name;

  @Schema(example = "Notebook com 16GB RAM e SSD 512GB")
  @NotBlank(message = "Descrição deve ter no mínimo 5 e no máximo 500 caracteres")
  @Size(min = 5, max = 500, message = "Descrição deve ter no mínimo 5 e no máximo 500 caracteres")
  private String description;

  @NotNull(message = "Marca do produto é obrigatória")
  private UUID brand;

  @NotNull(message = "Categoria do produto é obrigatória")
  private UUID category;

  public ProductRequestDTO(String name, String description, UUID brand, UUID category) {
    this.name = name;
    this.description = description;
    this.brand = brand;
    this.category = category;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public UUID getBrand() {
    return brand;
  }

  public UUID getCategory() {
    return category;
  }
}
