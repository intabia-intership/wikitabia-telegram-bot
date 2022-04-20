package com.intabia.wikibot.services.scenaries.abstractions;

import java.util.List;

/**
 * Интерфейс для добавления паттернов в контейнер.
 */
public interface ScenarioContainer {

  /**
   * Метод для добавления паттерна в контейнер.
   * @param scenario объект класса реализующего интерфейс Pattern.
   * @return this
   */
  ScenarioContainer addScenario(Scenario scenario);

  /**
   * Метод для получения контейнера.
   * @return
   */
  List<Scenario> getContainer();
}
