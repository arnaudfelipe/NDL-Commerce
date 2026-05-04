package com.ndlcommerce.useCase.request.product;

import lombok.Data;

import java.util.UUID;

@Data
public class ProductFilterDTO {

  private String name;
  private String description;
  private UUID brand;
  private UUID category;
}
