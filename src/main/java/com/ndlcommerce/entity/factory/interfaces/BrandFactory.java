package com.ndlcommerce.entity.factory.interfaces;

import com.ndlcommerce.entity.model.interfaces.Brand;

public interface BrandFactory {
  Brand create(String name);
}
