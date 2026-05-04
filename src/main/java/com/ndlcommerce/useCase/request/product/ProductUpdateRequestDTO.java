package com.ndlcommerce.useCase.request.product;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.UUID;

public class ProductUpdateRequestDTO {

    @Size(min = 3, max = 200, message = "Nome deve ter entre 3 e 200 caracteres")
    private String name;

    @Size(min = 5, max = 500, message = "Descrição deve ter no mínimo 5 e no máximo 500 caracteres")
    private String description;

    private UUID brand;

    private UUID category;

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
