package com.intabia.wikibot.exceptions;

public class FeignCommunicationException extends RuntimeException {
  public FeignCommunicationException(String reason) {
    super(String.format(ErrorMessage.FEIGN_COMMUNICATION_ERROR, reason));
  }
}
