package com.ndlcommerce.adapters.persistence.category;

import com.ndlcommerce.exception.BusinessException;
import com.ndlcommerce.exception.EntityAlreadyExistsException;
import com.ndlcommerce.useCase.interfaces.category.CategoryPresenter;
import com.ndlcommerce.useCase.request.category.CategoryResponseDTO;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.NoSuchElementException;

public class CategoryResponseFormatter implements CategoryPresenter {

  private static final Map<String, RuntimeException> ERRORS =
      Map.of(
          "NameNotValid",
          new BusinessException(
              "Nome da categoria não é válido, deve ter no mínimo 3 e no máximo 50 caracteres"),
          "DescriptionNotValid",
          new BusinessException(
              "Descrição da categoria não é válida, Descrição deve ter no mínimo 3 e no max 200 caracteres"),
          "ExistByName",
          new EntityAlreadyExistsException("Categoria já cadastrada"),
          "ParentNotFound",
          new NoSuchElementException("Categoria pai não encontrada"));

  @Override
  public CategoryResponseDTO prepareSuccessView(CategoryResponseDTO response) {

    LocalDateTime date = LocalDateTime.parse(response.getCreatedAt());

    response.setCreatedAt(date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));

    return response;
  }

  @Override
  public CategoryResponseDTO prepareFailView(String error) {

    RuntimeException runtimeException = ERRORS.get(error);

    if (runtimeException != null) {
      throw runtimeException;
    }

    throw new RuntimeException("Erro desconhecido: " + error);
  }
}
