package com.intabia.wikibot.exceptions;

public class HttpMethodNotFoundException extends RuntimeException{
  public HttpMethodNotFoundException(String message) {
    super(message);
  }
}
