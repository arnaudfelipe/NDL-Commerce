package com.ndlcommerce.useCase.interfaces.category;

import com.ndlcommerce.config.PaginatedResult;
import com.ndlcommerce.useCase.request.category.CategoryResponseDTO;

public interface CategoryPresenter {
  public CategoryResponseDTO prepareFailView(String nameNotValid);

  public CategoryResponseDTO prepareSuccessView(CategoryResponseDTO response);

  PaginatedResult<CategoryResponseDTO> prepareListSuccessView(
      PaginatedResult<CategoryResponseDTO> list);
}
