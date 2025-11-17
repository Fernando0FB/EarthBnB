package com.unesc.earthBnb.exception;

public class DataCheckinInvalidaException extends RuntimeException {
  public DataCheckinInvalidaException() {
    super("Data de check-in não pode ser após a data de check-out.");
  }
}