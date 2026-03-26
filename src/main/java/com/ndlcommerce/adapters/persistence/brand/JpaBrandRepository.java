package com.ndlcommerce.adapters.persistence.brand;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaBrandRepository extends JpaRepository<BrandDataMapper, UUID> {
  Optional<BrandDataMapper> findByIdAndActiveTrue(UUID uuid);

  boolean existsByNameAndActiveTrue(String name);

  boolean existsByNameAndIdNotAndActiveTrue(String name, UUID uuid);
}
