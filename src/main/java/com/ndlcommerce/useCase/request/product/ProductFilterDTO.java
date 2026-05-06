package com.ndlcommerce.useCase.request.product;

import java.util.UUID;
import lombok.Data;

@Data
public class ProductFilterDTO {

  private String name;
  private String description;
  private UUID brand;
  private UUID category;
}
