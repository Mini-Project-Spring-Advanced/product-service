package kh.com.kshrd.productservice.exception;

public class UnauthorizeException extends RuntimeException {
  public UnauthorizeException(String message) {
    super(message);
  }
}
