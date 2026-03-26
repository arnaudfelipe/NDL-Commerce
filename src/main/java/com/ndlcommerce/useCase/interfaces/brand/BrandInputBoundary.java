package com.ndlcommerce.useCase.interfaces.brand;


import com.ndlcommerce.config.PaginatedResult;
import com.ndlcommerce.useCase.request.brand.BrandRequestDTO;
import com.ndlcommerce.useCase.request.brand.BrandResponseDTO;

import java.util.UUID;

public interface BrandInputBoundary {
    BrandResponseDTO create(BrandRequestDTO requestDTO);

    PaginatedResult<?> list(BrandRequestDTO filter, int page, int size);

    BrandResponseDTO getById(UUID uuid);

    BrandResponseDTO updateBrand(UUID uuid, BrandRequestDTO requestDTO);

    Object deleteBrand(UUID brandId);
}