package com.intabia.wikibot.exceptions;

/**
 * Сообщения об ошибках.
 */
public interface ErrorMessage {
  String ILLEGAL_HTTP_CODE_ERROR = "Получили неверный HTTP код ответа. Ожидали: [%s], получили: [%s]";
  String FEIGN_COMMUNICATION_ERROR = "Проблемы при общении по feign клиенту: [%s]";
}
