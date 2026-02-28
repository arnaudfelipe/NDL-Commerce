package com.ndlcommerce.adapters.web;

import com.ndlcommerce.useCase.interfaces.category.CategoryInputBoundary;
import com.ndlcommerce.useCase.interfaces.product.ProductInputBoundary;
import com.ndlcommerce.useCase.request.category.CategoryRequestDTO;
import com.ndlcommerce.useCase.request.product.ProductRequestDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableMethodSecurity
@RequestMapping("/category")
@Tag(name = "Category")
public class CategoryController {


    private final CategoryInputBoundary categoryInputBoundary;

    public CategoryController(CategoryInputBoundary categoryInputBoundary) {
        this.categoryInputBoundary = categoryInputBoundary;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@Valid @RequestBody CategoryRequestDTO requestModel) {
        var productCreated = categoryInputBoundary.create(requestModel);
        return ResponseEntity.ok().body(productCreated);
    }
}
