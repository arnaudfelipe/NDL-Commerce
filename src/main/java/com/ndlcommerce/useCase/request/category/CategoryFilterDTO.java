package com.ndlcommerce.useCase.request.category;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CategoryFilterDTO {

  private String name;

  private UUID parentId;
}
