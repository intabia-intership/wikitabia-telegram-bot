package com.intabia.wikibot.exceptions;

public class MappingException extends RuntimeException {
  public MappingException(String message, Throwable e) {
    super(message, e);
  }
}
