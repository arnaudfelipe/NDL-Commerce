package com.ndlcommerce.useCase.request.category;

import java.util.UUID;
import lombok.Getter;

public record CategoryDbRequestDTO(@Getter String name, @Getter UUID parentId) {}
