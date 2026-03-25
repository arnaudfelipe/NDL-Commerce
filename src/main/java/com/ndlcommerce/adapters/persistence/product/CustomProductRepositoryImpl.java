package com.ndlcommerce.adapters.persistence.product;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomProductRepositoryImpl implements CustomProductRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<ProductDataMapper> search(
            String name, String description, String brand, Boolean active, Integer page, Integer size) {

//        TODO: colocar a brand aqui depois que finalizar o CRUD dela
        String jpql =
                """
                    SELECT p
                    FROM ProductDataMapper p
                    WHERE (:name IS NULL OR LOWER(p.name) LIKE LOWER(:name))
                    AND (:description IS NULL OR LOWER(COALESCE(p.description, "")) LIKE LOWER(:description))
                    AND   (:active IS NULL OR c.active = :active)
                """;

        TypedQuery<ProductDataMapper> query = entityManager.createQuery(jpql, ProductDataMapper.class);

        query.setParameter("name", name == null || name.isBlank() ? null : "%" + name + "%");
        query.setParameter(
                "description",
                description == null || description.isBlank() ? null : "%" + description + "%");
        query.setParameter("active", active);

        query.setFirstResult(page * size);
        query.setMaxResults(size);

        return query.getResultList();
    }
}