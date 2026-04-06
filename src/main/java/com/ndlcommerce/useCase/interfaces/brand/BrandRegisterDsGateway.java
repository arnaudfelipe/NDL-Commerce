package com.ndlcommerce.useCase.interfaces.brand;

import com.ndlcommerce.adapters.persistence.brand.BrandDataMapper;
import com.ndlcommerce.config.PaginatedResult;
import com.ndlcommerce.useCase.request.brand.BrandDbRequestDTO;
import java.util.Optional;
import java.util.UUID;

public interface BrandRegisterDsGateway {

  boolean existsByName(String name);

  BrandDataMapper save(BrandDbRequestDTO dbRequest);

  PaginatedResult<BrandDataMapper> list(BrandDbRequestDTO filter, int page, int size);

  Optional<BrandDataMapper> getById(UUID brandId);

  boolean existsByNameAndNotId(String name, UUID brandId);

  BrandDataMapper update(UUID brandId, BrandDbRequestDTO dbRequestDTO);

  void delete(UUID brandId);
}
