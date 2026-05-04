package com.ndlcommerce.adapters.persistence.product;

import com.ndlcommerce.adapters.persistence.category.CategoryDataMapper;
import com.ndlcommerce.adapters.persistence.user.UserDataMapper;
import com.ndlcommerce.config.PaginatedResult;
import com.ndlcommerce.config.SecurityFilter;
import com.ndlcommerce.useCase.interfaces.product.ProductRegisterDsGateway;
import com.ndlcommerce.useCase.request.product.ProductDbRequestDTO;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.ndlcommerce.useCase.request.product.ProductRequestDTO;
import com.ndlcommerce.useCase.request.product.ProductUpdateRequestDTO;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Component;

@Component
public class JpaProduct implements ProductRegisterDsGateway {

  private final JpaProductRepository repository;
  private final SecurityFilter securityFilter;

  public JpaProduct(JpaProductRepository repository, SecurityFilter securityFilter) {
    this.repository = repository;
    this.securityFilter = securityFilter;
  }

  @Override
  public boolean existsByName(String name) {
    return repository.existsByNameAndActive(name, true);
  }

  @Override
  public PaginatedResult<ProductDataMapper> list(ProductDbRequestDTO requestDTO, Integer page, Integer size) {

    ProductDataMapper dataMapper = new ProductDataMapper(
            requestDTO.getName(),
            requestDTO.getDescription(),
            requestDTO.getBrand(),
            requestDTO.getCategory(),
            null
    );

    ExampleMatcher matcher =
            ExampleMatcher.matching()
                    .withIgnoreCase()
                    .withIgnoreNullValues()
                    .withIgnorePaths("id", "createdBy", "createdAt", "updatedBy", "updatedAt")
                    .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);


    Example<ProductDataMapper> example = Example.of(dataMapper, matcher);

    Pageable pageable = PageRequest.of(page, size);

    Page<ProductDataMapper> categoryDataMapperPage = repository.findAll(example, pageable);

    PaginatedResult<ProductDataMapper> resultCategoryDataMapper =
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
  public ProductDataMapper save(ProductDbRequestDTO requestDTO) {

    UserDataMapper userLogado = securityFilter.obterUsuarioLogado();

    ProductDataMapper entity =
        new ProductDataMapper(
            requestDTO.getName(),
                requestDTO.getDescription(),
                requestDTO.getBrand(),
                requestDTO.getCategory(),
                userLogado.getId());

    return repository.save(entity);
  }

  @Override
  public Optional<ProductDataMapper> findById(UUID uuid) {
    return repository.findByIdAndActive(uuid, true);
  }

  @Override
  public boolean existsByNameAndIdNot(String name, UUID uuid) {
    return repository.existsByNameAndIdNotAndActive(name, uuid, true);
  }

  @Override
  public ProductDataMapper update(ProductDataMapper productDataMapper, ProductUpdateRequestDTO requestDTO) {

    productDataMapper.setName(requestDTO.getName() == null ? productDataMapper.getName() : requestDTO.getName());
    productDataMapper.setDescription(requestDTO.getDescription() == null ? productDataMapper.getDescription() : requestDTO.getDescription());
    productDataMapper.setBrandId(requestDTO.getBrand() == null ? productDataMapper.getBrandId() : requestDTO.getBrand());
    productDataMapper.setCategoryId(requestDTO.getCategory() == null ? productDataMapper.getCategoryId() : requestDTO.getCategory());
    return repository.save(productDataMapper);
  }

  @Override
  public void delete(UUID productId) {
    ProductDataMapper ProductDataMapper = findById(productId).get();
    ProductDataMapper.setActive(false);
    repository.save(ProductDataMapper);
  }

}
