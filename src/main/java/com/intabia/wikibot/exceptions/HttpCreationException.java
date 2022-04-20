package com.intabia.wikibot.exceptions;

public class HttpCreationException extends RuntimeException{
  public HttpCreationException(String message, Throwable exception) {
    super(message, exception);
  }
  public HttpCreationException(String message) {
    super(message);
  }
}
