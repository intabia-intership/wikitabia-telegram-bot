package com.intabia.wikibot.exceptions;

import org.springframework.http.HttpStatus;

public class FeignIllegalResponseHttpCodeException extends RuntimeException {
  public FeignIllegalResponseHttpCodeException(HttpStatus expected, HttpStatus actual) {
    super(String.format(ErrorMessage.ILLEGAL_HTTP_CODE_ERROR, expected, actual));
  }
}
