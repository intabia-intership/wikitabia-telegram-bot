package com.intabia.wikibot.exceptions;

public class ReflectionException extends RuntimeException{
  public ReflectionException(String message) {
    super(message);
  }
  public ReflectionException(String message, Throwable exception) {
    super(message, exception);
  }
}
