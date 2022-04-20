package com.intabia.wikibot.services.scenaries.abstractions;

import com.intabia.wikibot.dto.telegram.UpdateDto;

/**
 * интерфейс для создания паттернов.
 */
public interface Scenario {

  /**
   * метод содержащий последовательность работы бота.
   * @param update dto от ответа телеграм на наличие обновлений
   * @param botToken уникальный токен бота
   */
  void doScenario(UpdateDto update, String botToken);

  /**
   * обязательный метод для фильтрации сценариев.
   * класс имплементирующий этот интерфейс обязан иметь поле с сообщением пользователя,
   * которое он отправляет в чат.
   * @return возвращает сообщение пользователя.
   */
  String getInvokeMessage();
}
