package com.ndlcommerce.useCase;

import com.ndlcommerce.config.PaginatedResult;
import com.ndlcommerce.useCase.interfaces.brand.BrandInputBoundary;
import com.ndlcommerce.useCase.request.brand.BrandRequestDTO;
import com.ndlcommerce.useCase.request.brand.BrandResponseDTO;

import java.util.UUID;

public class BrandRegisterInteractor implements BrandInputBoundary {
    @Override
    public BrandResponseDTO create(BrandRequestDTO requestDTO) {
        return null;
    }

    @Override
    public PaginatedResult<?> list(BrandRequestDTO filter, int page, int size) {
        return null;
    }

    @Override
    public BrandResponseDTO getById(UUID uuid) {
        return null;
    }

    @Override
    public BrandResponseDTO updateCategory(UUID uuid, BrandRequestDTO requestDTO) {
        return null;
    }

    @Override
    public Object deleteCategory(UUID categoryId) {
        return null;
    }
}
