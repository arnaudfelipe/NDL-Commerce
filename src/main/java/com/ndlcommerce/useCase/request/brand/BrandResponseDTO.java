package com.ndlcommerce.useCase.request.brand;

import lombok.Setter;

import java.util.UUID;

public class BrandResponseDTO {

    private final UUID id;
    private final String name;
    @Setter
    private String createdAt;


    public BrandResponseDTO(UUID id, String name, String createdAt) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCreatedAt() {
        return createdAt;
    }

}
