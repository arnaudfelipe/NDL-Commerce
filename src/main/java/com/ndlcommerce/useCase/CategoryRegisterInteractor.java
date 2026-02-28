package com.ndlcommerce.useCase;

import com.ndlcommerce.adapters.persistence.category.CategoryDataMapper;
import com.ndlcommerce.entity.factory.interfaces.CategoryFactory;
import com.ndlcommerce.entity.model.interfaces.Category;
import com.ndlcommerce.useCase.interfaces.category.CategoryInputBoundary;
import com.ndlcommerce.useCase.interfaces.category.CategoryPresenter;
import com.ndlcommerce.useCase.interfaces.category.CategoryRegisterDsGateway;
import com.ndlcommerce.useCase.request.category.CategoryDbRequestDTO;
import com.ndlcommerce.useCase.request.category.CategoryRequestDTO;
import com.ndlcommerce.useCase.request.category.CategoryResponseDTO;

public class CategoryRegisterInteractor implements CategoryInputBoundary {

  private final CategoryRegisterDsGateway categoryDsGateway;
  private final CategoryPresenter categoryPresenter;
  private final CategoryFactory categoryFactory;

  public CategoryRegisterInteractor(
      CategoryRegisterDsGateway categoryDsGateway,
      CategoryPresenter categoryPresenter,
      CategoryFactory categoryFactory) {
    this.categoryDsGateway = categoryDsGateway;
    this.categoryPresenter = categoryPresenter;
    this.categoryFactory = categoryFactory;
  }

  @Override
  public CategoryResponseDTO create(CategoryRequestDTO requestDTO) {

    Category category = categoryFactory.create(requestDTO.getName());

    if (!category.nameIsValid()) {
      return categoryPresenter.prepareFailView("NameNotValid");
    }


    if (categoryDsGateway.existsByName(category.name())) {
      return categoryPresenter.prepareFailView("ExistByName");
    }

    if (requestDTO.getParentId() != null
        && !categoryDsGateway.existsById(requestDTO.getParentId())) {
      return categoryPresenter.prepareFailView("ParentNotFound");
    }

    CategoryDbRequestDTO dbRequest =
        new CategoryDbRequestDTO(
            category.name(), requestDTO.getParentId());

    CategoryDataMapper saved = categoryDsGateway.save(dbRequest);

    CategoryResponseDTO response =
        new CategoryResponseDTO(
            saved.getId(), saved.getName(), saved.getParentId(), saved.getCreatedAt().toString());

    return categoryPresenter.prepareSuccessView(response);
  }
}
