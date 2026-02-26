package com.ndlcommerce.entity.model.interfaces;

public interface Category {

  String getName();

  String getDescription();

  boolean nameIsValid();

  boolean descriptionIsValid();
}
