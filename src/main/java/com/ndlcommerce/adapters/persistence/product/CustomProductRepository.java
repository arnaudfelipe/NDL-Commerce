package com.ndlcommerce.adapters.persistence.product;


import java.util.List;

public interface CustomProductRepository  {
    List<ProductDataMapper> search(
            String name, String description, String brand, Boolean active, Integer page, Integer size);
}
