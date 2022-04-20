package com.intabia.wikibot.services.scenaries;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.intabia.wikibot.services.scenaries.abstractions.Scenario;
import com.intabia.wikibot.services.scenaries.abstractions.ScenarioContainer;
import com.intabia.wikibot.services.scenaries.abstractions.ScenarioDispatcher;

/**
 * класс для определения паттерна в контейнере паттернов.
 */
@Component
public class ScenarioDispatcherImpl implements ScenarioDispatcher {

  private final ScenarioContainer patternsContainer;

  private ScenarioDispatcherImpl(@Autowired ScenarioContainerImpl patternsContainer) {
    this.patternsContainer = patternsContainer;
  }

  public Scenario findScenario(String messageFromUser) {
    List<Scenario> scenarios = patternsContainer.getContainer().stream()
        .filter(x -> messageFromUser.startsWith(x.getInvokeMessage()))
        .collect(Collectors.toList());
    if (scenarios.isEmpty()) {
      return null;
    }
    return scenarios.get(0);
  }
}