package com.ndlcommerce.useCase.interfaces.product;

import com.ndlcommerce.config.PaginatedResult;
import com.ndlcommerce.useCase.request.product.ProductResponseDTO;

public interface ProductPresenter {

  ProductResponseDTO prepareSuccessView(ProductResponseDTO product);

  ProductResponseDTO prepareFailView(String error);

  PaginatedResult<ProductResponseDTO> prepareListSuccessView(
      PaginatedResult<ProductResponseDTO> list);
}
