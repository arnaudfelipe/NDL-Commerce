package com.ndlcommerce.adapters.persistence.category;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaCategoryRepository extends JpaRepository<CategoryDataMapper, UUID> {

    boolean existsByName (String name);

}
