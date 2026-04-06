package com.ndlcommerce.adapters.persistence.brand;

import com.ndlcommerce.adapters.persistence.user.UserDataMapper;
import com.ndlcommerce.config.PaginatedResult;
import com.ndlcommerce.config.SecurityFilter;
import com.ndlcommerce.useCase.interfaces.brand.BrandRegisterDsGateway;
import com.ndlcommerce.useCase.request.brand.BrandDbRequestDTO;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.*;

public class JpaBrand implements BrandRegisterDsGateway {

  private final JpaBrandRepository repository;
  private final SecurityFilter securityFilter;

  public JpaBrand(JpaBrandRepository repository, SecurityFilter securityFilter) {
    this.repository = repository;
    this.securityFilter = securityFilter;
  }

  @Override
  public boolean existsByName(String name) {
    return repository.existsByNameAndActiveTrue(name);
  }

  @Override
  public BrandDataMapper save(BrandDbRequestDTO dbRequest) {
    UserDataMapper userLogado = securityFilter.obterUsuarioLogado();
    BrandDataMapper brandDataMapper = new BrandDataMapper(dbRequest.name(), userLogado.getId());
    return repository.save(brandDataMapper);
  }

  @Override
  public PaginatedResult<BrandDataMapper> list(BrandDbRequestDTO filter, int page, int size) {
    BrandDataMapper dataMapper = new BrandDataMapper(filter.getName());

    ExampleMatcher matcher =
        ExampleMatcher.matching()
            .withIgnoreCase()
            .withIgnoreNullValues()
            .withIgnorePaths("id", "createdBy", "createdAt", "updatedBy", "updatedAt")
            .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

    Example<BrandDataMapper> example = Example.of(dataMapper, matcher);

    Pageable pageable = PageRequest.of(page, size);
    Page<BrandDataMapper> pageResult = repository.findAll(example, pageable);

    return new PaginatedResult<>(
        pageResult.getContent(),
        pageResult.getNumber(),
        pageResult.getSize(),
        pageResult.getTotalElements(),
        pageResult.getTotalPages(),
        pageResult.isFirst(),
        pageResult.isLast());
  }

  @Override
  public Optional<BrandDataMapper> getById(UUID uuid) {
    return repository.findByIdAndActiveTrue(uuid);
  }

  @Override
  public boolean existsByNameAndNotId(String name, UUID uuid) {
    return repository.existsByNameAndIdNotAndActiveTrue(name, uuid);
  }

  @Override
  public BrandDataMapper update(UUID brandId, BrandDbRequestDTO dbRequestDTO) {
    Optional<BrandDataMapper> optional = repository.findByIdAndActiveTrue(brandId);

    if (optional.isEmpty()) {
      return null;
    }

    BrandDataMapper brandDataMapper = optional.get();
    UserDataMapper userLogado = securityFilter.obterUsuarioLogado();

    brandDataMapper.setName(
        dbRequestDTO.getName().isEmpty() ? brandDataMapper.getName() : dbRequestDTO.getName());
    brandDataMapper.setUpdatedBy(userLogado.getId());

    return repository.save(brandDataMapper);
  }

  @Override
  public void delete(UUID brandId) {
    Optional<BrandDataMapper> optional = repository.findByIdAndActiveTrue(brandId);
    if (optional.isEmpty()) {
      return;
    }

    BrandDataMapper brandDataMapper = optional.get();
    UserDataMapper userLogado = securityFilter.obterUsuarioLogado();

    brandDataMapper.setUpdatedBy(userLogado.getId());
    brandDataMapper.setActive(false);

    repository.save(brandDataMapper);
  }
}
