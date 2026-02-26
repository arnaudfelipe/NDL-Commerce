package com.ndlcommerce.useCase.interfaces.category;

import com.ndlcommerce.adapters.persistence.category.CategoryDataMapper;
import com.ndlcommerce.useCase.request.category.CategoryDbRequestDTO;
import java.util.UUID;

public interface CategoryRegisterDsGateway {
  boolean existsByName(String name);

  CategoryDataMapper save(CategoryDbRequestDTO dbRequest);

  boolean existsById(UUID parentId);
}
