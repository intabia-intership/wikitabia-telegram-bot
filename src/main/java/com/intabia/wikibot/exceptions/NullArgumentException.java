package com.intabia.wikibot.exceptions;

public class NullArgumentException extends RuntimeException {
  public NullArgumentException(String message) {
    super(message);
  }
}
