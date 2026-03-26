package com.ndlcommerce.adapters.persistence.brand;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaBrandRepository extends JpaRepository<BrandDataMapper, UUID> {

  boolean existsByNameAndActiveTrue(String name);

  boolean existsByNameAndIdNotAndActiveTrue(String name, UUID uuid);
}
