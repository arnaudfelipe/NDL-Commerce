package com.ndlcommerce.useCase;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import com.ndlcommerce.adapters.persistence.brand.BrandDataMapper;
import com.ndlcommerce.config.PaginatedResult;
import com.ndlcommerce.entity.factory.interfaces.BrandFactory;
import com.ndlcommerce.entity.model.implementation.CommonBrand;
import com.ndlcommerce.entity.model.interfaces.Brand;
import com.ndlcommerce.useCase.interfaces.brand.BrandPresenter;
import com.ndlcommerce.useCase.interfaces.brand.BrandRegisterDsGateway;
import com.ndlcommerce.useCase.request.brand.BrandDbRequestDTO;
import com.ndlcommerce.useCase.request.brand.BrandRequestDTO;
import com.ndlcommerce.useCase.request.brand.BrandResponseDTO;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BrandRegisterInteractorTests {

  private BrandRegisterDsGateway brandDsGateway;
  private BrandPresenter brandPresenter;
  private BrandFactory brandFactory;
  private BrandRegisterInteractor interactor;

  @BeforeEach
  void setUp() {
    brandDsGateway = mock(BrandRegisterDsGateway.class);
    brandPresenter = mock(BrandPresenter.class);
    brandFactory = mock(BrandFactory.class);

    interactor = new BrandRegisterInteractor(brandDsGateway, brandPresenter, brandFactory);
  }

  @Test
  void givenValidName_whenCreate_thenSaveAndPrepareSuccessView() {
    Brand brand = new CommonBrand("Nike");
    BrandRequestDTO requestDTO = new BrandRequestDTO("Nike");

    BrandDataMapper saved = new BrandDataMapper("Nike", UUID.randomUUID());
    saved.setId(UUID.randomUUID());
    saved.setCreatedAt(LocalDateTime.now());

    when(brandFactory.create(anyString())).thenReturn(brand);
    when(brandDsGateway.existsByName("Nike")).thenReturn(false);
    when(brandDsGateway.save(any(BrandDbRequestDTO.class))).thenReturn(saved);

    interactor.create(requestDTO);

    verify(brandDsGateway, times(1)).save(any(BrandDbRequestDTO.class));
    verify(brandPresenter, times(1)).prepareSuccessView(any(BrandResponseDTO.class));
    verify(brandPresenter, never()).prepareFailView(anyString());
  }

  @Test
  void givenInvalidName_whenCreate_thenPrepareFailViewAndDoNotSave() {
    Brand brand = new CommonBrand("A");
    BrandRequestDTO requestDTO = new BrandRequestDTO("A");

    when(brandFactory.create(anyString())).thenReturn(brand);

    interactor.create(requestDTO);

    verify(brandPresenter, times(1)).prepareFailView("NameNotValid");
    verify(brandDsGateway, never()).save(any(BrandDbRequestDTO.class));
    verify(brandPresenter, never()).prepareSuccessView(any(BrandResponseDTO.class));
  }

  @Test
  void givenNotFoundBrandId_whenUpdate_thenPrepareFailView() {
    UUID id = UUID.randomUUID();
    BrandRequestDTO requestDTO = new BrandRequestDTO("Adidas");

    when(brandDsGateway.getById(id)).thenReturn(Optional.empty());

    interactor.update(id, requestDTO);

    verify(brandPresenter, times(1)).prepareFailView("NotFound");
    verify(brandDsGateway, never()).update(any(UUID.class), any(BrandDbRequestDTO.class));
  }

  @Test
  void givenFiltersAndPagination_whenList_thenPrepareListSuccessView() {
    BrandDataMapper mapper = new BrandDataMapper("Puma", UUID.randomUUID());
    mapper.setId(UUID.randomUUID());
    mapper.setCreatedAt(LocalDateTime.now());

    PaginatedResult<BrandDataMapper> page =
        new PaginatedResult<>(List.of(mapper), 0, 10, 1, 1, true, true);

    when(brandDsGateway.list(any(BrandDbRequestDTO.class), anyInt(), anyInt())).thenReturn(page);

    interactor.list(new BrandRequestDTO("Pu"), 0, 10);

    verify(brandDsGateway, times(1)).list(any(BrandDbRequestDTO.class), eq(0), eq(10));
    verify(brandPresenter, times(1)).prepareListSuccessView(any());
  }

  @Test
  void givenExistingBrandId_whenDelete_thenDeleteAndPrepareSuccessView() {
    UUID id = UUID.randomUUID();

    BrandDataMapper mapper = new BrandDataMapper("Olympikus", UUID.randomUUID());
    mapper.setId(id);
    mapper.setCreatedAt(LocalDateTime.now());

    when(brandDsGateway.getById(id)).thenReturn(Optional.of(mapper));

    interactor.delete(id);

    verify(brandDsGateway, times(1)).delete(id);
    verify(brandPresenter, times(1)).prepareSuccessView(null);
    verify(brandPresenter, never()).prepareFailView(anyString());
  }
}
