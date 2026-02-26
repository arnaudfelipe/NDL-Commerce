package com.ndlcommerce.entity.model.implementation;

import com.ndlcommerce.entity.model.interfaces.Category;

public class CommonCategory implements Category {

  private String name;
  private String description;

  public CommonCategory(String name, String description) {
    this.name = name;
    this.description = description;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public String getDescription() {
    return this.description;
  }

  @Override
  public boolean nameIsValid() {
    return this.name != null
        && !this.name.isBlank()
        && this.name.length() >= 3
        && this.name.length() < 50;
  }

  @Override
  public boolean descriptionIsValid() {
    return this.description != null
        && !this.description.isBlank()
        && this.description.length() >= 3
        && this.description.length() < 200;
  }
}
