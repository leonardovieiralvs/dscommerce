package com.lsouzadev.dscommerce.exceptions;

public class DatabaseViolationException extends RuntimeException {
  public DatabaseViolationException(String message) {
    super(message);
  }
}
