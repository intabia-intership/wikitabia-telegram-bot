package com.intabia.wikibot.services.starter;

import java.io.IOException;

/**
 * public api для взаимодействия с ботом
 */
public interface BotStarter {

  /**
   * Метод для запуска бота.
   * NB-перед запуском требуется конфигурация через интерфейс PatternContainer.
   * @param botToken уникальный токен бота.
   */
  void startBot(String botToken) throws IOException;
}
