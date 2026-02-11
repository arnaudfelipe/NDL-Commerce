package com.ndlcommerce.config.exception;

import lombok.Getter;

@Getter
public class InvalidFieldException extends RuntimeException {
  private final String field;

  public InvalidFieldException(String field, String message) {
    super(message);
    this.field = field;
  }
}
