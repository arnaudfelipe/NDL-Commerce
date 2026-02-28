package com.ndlcommerce.useCase.interfaces.category;

import com.ndlcommerce.config.PaginatedResult;
import com.ndlcommerce.useCase.request.category.CategoryFilterDTO;
import com.ndlcommerce.useCase.request.category.CategoryRequestDTO;
import com.ndlcommerce.useCase.request.category.CategoryResponseDTO;
import java.util.UUID;

public interface CategoryInputBoundary {
  CategoryResponseDTO create(CategoryRequestDTO requestDTO);

  PaginatedResult<?> list(CategoryFilterDTO filter, int page, int size);

  CategoryResponseDTO getById(UUID uuid);
}
