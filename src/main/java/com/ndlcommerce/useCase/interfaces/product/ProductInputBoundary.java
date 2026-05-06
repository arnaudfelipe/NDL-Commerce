package com.ndlcommerce.useCase.interfaces.product;

import com.ndlcommerce.config.PaginatedResult;
import com.ndlcommerce.useCase.request.product.ProductFilterDTO;
import com.ndlcommerce.useCase.request.product.ProductRequestDTO;
import com.ndlcommerce.useCase.request.product.ProductResponseDTO;
import com.ndlcommerce.useCase.request.product.ProductUpdateRequestDTO;
import java.util.UUID;

public interface ProductInputBoundary {

  ProductResponseDTO create(ProductRequestDTO requestDTO);

  PaginatedResult<ProductResponseDTO> list(ProductFilterDTO filter, int page, int size);

  ProductResponseDTO getById(UUID productId);

  ProductResponseDTO updateProduct(UUID productId, ProductUpdateRequestDTO requestDTO);

  ProductResponseDTO deleteProduct(UUID productId);
}
