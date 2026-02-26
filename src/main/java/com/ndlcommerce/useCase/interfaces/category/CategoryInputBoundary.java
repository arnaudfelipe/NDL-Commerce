package com.ndlcommerce.useCase.interfaces.category;

import com.ndlcommerce.useCase.request.category.CategoryRequestDTO;
import com.ndlcommerce.useCase.request.category.CategoryResponseDTO;

public interface CategoryInputBoundary {
  CategoryResponseDTO create(CategoryRequestDTO requestDTO);
}
