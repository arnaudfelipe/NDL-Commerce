package com.ndlcommerce.useCase.interfaces.product;

import com.ndlcommerce.adapters.persistence.product.ProductDataMapper;
import com.ndlcommerce.config.PaginatedResult;
import com.ndlcommerce.useCase.request.product.ProductDbRequestDTO;
import com.ndlcommerce.useCase.request.product.ProductUpdateRequestDTO;
import java.util.Optional;
import java.util.UUID;

public interface ProductRegisterDsGateway {

  boolean existsByName(String name);

  PaginatedResult<ProductDataMapper> list(
      ProductDbRequestDTO requestDTO, Integer page, Integer size);

  ProductDataMapper save(ProductDbRequestDTO requestDTO);

  Optional<ProductDataMapper> findById(UUID uuid);

  boolean existsByNameAndIdNot(String name, UUID uuid);

  ProductDataMapper update(ProductDataMapper productDataMapper, ProductUpdateRequestDTO requestDTO);

  void delete(UUID productId);
}
