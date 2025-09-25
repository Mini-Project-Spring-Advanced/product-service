package kh.com.kshrd.productservice.exception;

public class ServerErrorException extends RuntimeException {
  public ServerErrorException(String message) {
    super(message);
  }
}
