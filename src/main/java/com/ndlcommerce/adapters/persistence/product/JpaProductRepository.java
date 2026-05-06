package com.ndlcommerce.adapters.persistence.product;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaProductRepository extends JpaRepository<ProductDataMapper, UUID> {

  boolean existsByNameAndActive(String name, boolean active);

  boolean existsByNameAndIdNotAndActive(String name, UUID uuid, boolean active);

  Optional<ProductDataMapper> findByIdAndActive(UUID id, boolean active);
}
