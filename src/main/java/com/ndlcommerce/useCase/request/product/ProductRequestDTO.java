package com.ndlcommerce.useCase.request.product;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public class ProductRequestDTO {

    @Schema(example = "Notebook Dell Inspiron")
    @NotBlank(message = "Nome do produto é obrigatório")
    @Size(min = 3, max = 200, message = "Nome deve ter entre 3 e 200 caracteres")
    private String name;

    @Schema(example = "Notebook com 16GB RAM e SSD 512GB")
    @NotBlank(message = "Descrição do produto é obrigatória")
    @Size(min = 5, max = 200, message = "Descrição deve ter no mínimo 5 e no máximo 200 caracteres")
    private String description;


//    @NotNull(message = "Necessário informar a Marca do produto")
//    TODO: a marcar n tem CRUD ainda, depois colcoar essa validação
    UUID brand;




  public ProductRequestDTO(String name, String description, UUID brand) {
    this.name = name;
    this.description = description;
    this.brand = brand;
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

}
