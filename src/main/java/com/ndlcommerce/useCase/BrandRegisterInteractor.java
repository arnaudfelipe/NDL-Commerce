package com.ndlcommerce.useCase;

import com.ndlcommerce.adapters.persistence.brand.BrandDataMapper;
import com.ndlcommerce.config.PaginatedResult;
import com.ndlcommerce.entity.factory.interfaces.BrandFactory;
import com.ndlcommerce.entity.model.interfaces.Brand;
import com.ndlcommerce.useCase.interfaces.brand.BrandInputBoundary;
import com.ndlcommerce.useCase.interfaces.brand.BrandPresenter;
import com.ndlcommerce.useCase.interfaces.brand.BrandRegisterDsGateway;
import com.ndlcommerce.useCase.request.brand.BrandDbRequestDTO;
import com.ndlcommerce.useCase.request.brand.BrandRequestDTO;
import com.ndlcommerce.useCase.request.brand.BrandResponseDTO;
import java.util.Optional;
import java.util.UUID;

public class BrandRegisterInteractor implements BrandInputBoundary {

  private final BrandRegisterDsGateway brandDsGateway;
  private final BrandPresenter brandPresenter;
  private final BrandFactory brandFactory;

  public BrandRegisterInteractor(
      BrandRegisterDsGateway brandDsGateway,
      BrandPresenter brandPresenter,
      BrandFactory brandFactory) {
    this.brandDsGateway = brandDsGateway;
    this.brandPresenter = brandPresenter;
    this.brandFactory = brandFactory;
  }

  @Override
  public BrandResponseDTO create(BrandRequestDTO requestDTO) {
    Brand brand = brandFactory.create(requestDTO.name());

    if (!brand.nameIsValid()) {
      return brandPresenter.prepareFailView("NameNotValid");
    }

    if (brandDsGateway.existsByName(brand.getName())) {
      return brandPresenter.prepareFailView("ExistByName");
    }

    BrandDbRequestDTO dbRequest = new BrandDbRequestDTO(brand.getName());
    BrandDataMapper saved = brandDsGateway.save(dbRequest);

    BrandResponseDTO response =
        new BrandResponseDTO(saved.getId(), saved.getName(), saved.getCreatedAt().toString());

    return brandPresenter.prepareSuccessView(response);
  }

  @Override
  public PaginatedResult<?> list(BrandRequestDTO filter, int page, int size) {
    BrandDbRequestDTO request = new BrandDbRequestDTO(filter != null ? filter.name() : null);

    PaginatedResult<BrandDataMapper> result = brandDsGateway.list(request, page, size);

    PaginatedResult<BrandResponseDTO> response = result == null ? null : result.map(this::mapperToDTO);

    return brandPresenter.prepareListSuccessView(response);
  }

  @Override
  public BrandResponseDTO getById(UUID uuid) {
    Optional<BrandDataMapper> optional = brandDsGateway.getById(uuid);

    if (optional.isEmpty()) {
      return brandPresenter.prepareFailView("NotFound");
    }

    BrandResponseDTO responseDTO = mapperToDTO(optional.get());

    return brandPresenter.prepareSuccessView(responseDTO);
  }

  @Override
  public BrandResponseDTO updateBrand(UUID uuid, BrandRequestDTO requestDTO) {
    Optional<BrandDataMapper> optional = brandDsGateway.getById(uuid);

    if (optional.isEmpty()) {
      return brandPresenter.prepareFailView("NotFound");
    }

    Brand brand = brandFactory.create(requestDTO.name());

    if (!brand.nameIsValid()) {
      return brandPresenter.prepareFailView("NameNotValid");
    }

    if (brandDsGateway.existsByNameAndNotId(brand.getName(), uuid)) {
      return brandPresenter.prepareFailView("ExistByName");
    }

    BrandDbRequestDTO request = new BrandDbRequestDTO(brand.getName());
    BrandDataMapper updated = brandDsGateway.update(uuid, request);

    return brandPresenter.prepareSuccessView(mapperToDTO(updated));
  }

  @Override
  public Object deleteBrand(UUID brandId) {
    Optional<BrandDataMapper> optional = brandDsGateway.getById(brandId);

    if (optional.isEmpty()) {
      return brandPresenter.prepareFailView("NotFound");
    }

    brandDsGateway.delete(brandId);

    return brandPresenter.prepareSuccessView(null);
  }

  private BrandResponseDTO mapperToDTO(BrandDataMapper mapper) {
    return new BrandResponseDTO(mapper.getId(), mapper.getName(), mapper.getCreatedAt().toString());
  }
}
