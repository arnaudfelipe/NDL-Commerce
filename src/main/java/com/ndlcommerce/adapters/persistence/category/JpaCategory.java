package com.ndlcommerce.adapters.persistence.category;

import com.ndlcommerce.adapters.persistence.user.UserDataMapper;
import com.ndlcommerce.config.PaginatedResult;
import com.ndlcommerce.config.SecurityFilter;
import com.ndlcommerce.useCase.interfaces.category.CategoryRegisterDsGateway;
import com.ndlcommerce.useCase.request.category.CategoryDbRequestDTO;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.*;

public class JpaCategory implements CategoryRegisterDsGateway {
  private final JpaCategoryRepository repository;
  private final SecurityFilter securityFilter;

  public JpaCategory(JpaCategoryRepository repository, SecurityFilter securityFilter) {
    this.repository = repository;
    this.securityFilter = securityFilter;
  }

  @Override
  public boolean existsByName(String name) {
    return repository.existsByNameAndActiveTrue(name);
  }

  @Override
  public CategoryDataMapper save(CategoryDbRequestDTO dbRequest) {

    UserDataMapper userLogado = securityFilter.obterUsuarioLogado();

    CategoryDataMapper categoryDataMapper =
        new CategoryDataMapper(dbRequest.name(), dbRequest.parentId(), userLogado.getId());
    return repository.save(categoryDataMapper);
  }

  @Override
  public boolean existsById(UUID parentId) {
    return repository.existsById(parentId);
  }

  @Override
  public PaginatedResult<CategoryDataMapper> list(
      CategoryDbRequestDTO request, int page, int size) {

    CategoryDataMapper dataMapper =
        new CategoryDataMapper(request.getName(), request.getParentId());

    ExampleMatcher matcher =
        ExampleMatcher.matching()
            .withIgnoreCase()
            .withIgnoreNullValues()
            .withIgnorePaths("id", "active", "createdBy", "createdAt", "updatedBy", "updatedAt")
            .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

    Example<CategoryDataMapper> example = Example.of(dataMapper, matcher);

    Pageable pageable = PageRequest.of(page, size);

    Page<CategoryDataMapper> categoryDataMapperPage = repository.findAll(example, pageable);

    PaginatedResult<CategoryDataMapper> resultCategoryDataMapper =
        new PaginatedResult<>(
            categoryDataMapperPage.getContent(),
            categoryDataMapperPage.getNumber(),
            categoryDataMapperPage.getSize(),
            categoryDataMapperPage.getTotalElements(),
            categoryDataMapperPage.getTotalPages(),
            categoryDataMapperPage.isFirst(),
            categoryDataMapperPage.isLast());

    return resultCategoryDataMapper;
  }

  @Override
  public Optional<CategoryDataMapper> getById(UUID uuid) {
    return repository.findById(uuid);
  }
}
