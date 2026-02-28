package com.ndlcommerce.adapters.persistence.category;

import com.ndlcommerce.adapters.persistence.user.UserDataMapper;
import com.ndlcommerce.config.SecurityFilter;
import com.ndlcommerce.useCase.interfaces.category.CategoryRegisterDsGateway;
import com.ndlcommerce.useCase.request.category.CategoryDbRequestDTO;

import java.util.UUID;

public class JpaCategory implements CategoryRegisterDsGateway {
    private final JpaCategoryRepository repository;
    private final SecurityFilter securityFilter;

    public JpaCategory(JpaCategoryRepository repository, SecurityFilter securityFilter) {
        this.repository = repository;
        this.securityFilter = securityFilter;
    }

    @Override
    public boolean existsByName(String name) {
        return repository.existsByName(name);
    }

    @Override
    public CategoryDataMapper save(CategoryDbRequestDTO dbRequest) {

        UserDataMapper userLogado = securityFilter.obterUsuarioLogado();

        CategoryDataMapper categoryDataMapper = new CategoryDataMapper(
            dbRequest.name(),
                dbRequest.parentId(),
                userLogado.getId()
        );
        return repository.save(categoryDataMapper);
    }

    @Override
    public boolean existsById(UUID parentId) {
        return repository.existsById(parentId);
    }

}
