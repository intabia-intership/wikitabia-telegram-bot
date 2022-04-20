package com.intabia.wikibot.dto;

/**
 * Интерфейс, который должен быть реализован в dto.
 */
public interface ReadableForUsers {
  /**
   * метод для текстового представления объекта пользователю телеграм.
   * @return читаемая строка.
   */
  String toReadableString();
}
