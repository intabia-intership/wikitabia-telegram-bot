package com.intabia.wikibot.services.scenaries;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import com.intabia.wikibot.services.scenaries.abstractions.Scenario;
import com.intabia.wikibot.services.scenaries.abstractions.ScenarioContainer;

/**
 * Класс для хранения паттернов.
 */
@Component()
public class ScenarioContainerImpl implements ScenarioContainer {

  private final List<Scenario> scenarios;

  public ScenarioContainerImpl() {
    this.scenarios = new ArrayList<>();
  }

  public List<Scenario> getContainer() {
    return scenarios;
  }

  public ScenarioContainer addScenario(Scenario scenario) {
    scenarios.add(scenario);
    return this;
  }
}
