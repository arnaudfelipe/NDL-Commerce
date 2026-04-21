package com.ndlcommerce.entity.factory.implementation;

import com.ndlcommerce.entity.factory.interfaces.BrandFactory;
import com.ndlcommerce.entity.model.implementation.CommonBrand;
import com.ndlcommerce.entity.model.interfaces.Brand;

public class CommonBrandFactoryImp implements BrandFactory {

  @Override
  public Brand create(String name) {
    return new CommonBrand(name);
  }
}
