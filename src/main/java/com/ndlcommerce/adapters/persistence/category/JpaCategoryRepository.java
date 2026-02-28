package com.ndlcommerce.adapters.persistence.category;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaCategoryRepository extends JpaRepository<CategoryDataMapper, UUID> {

  boolean existsByNameAndActiveTrue(String name);
}
