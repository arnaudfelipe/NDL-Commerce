package com.ndlcommerce.adapters.persistence.product;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaProductRepository extends JpaRepository<ProductDataMapper, UUID>, CustomProductRepository {

  boolean existsByName(String name);

  boolean existsByNameAndIdNot(String name, UUID id);

    @Override
    List<ProductDataMapper> search(String name, String description, String brand, Boolean active, Integer page, Integer size);
}
