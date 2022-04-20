package com.intabia.wikibot.services.scenaries;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import com.intabia.wikibot.services.scenaries.abstractions.ScenarioContainer;
import com.intabia.wikibot.services.scenaries.abstractions.ScenarioService;
import com.intabia.wikibot.services.scenaries.implemetations.wikitabia.CreateResourceScenario;
import com.intabia.wikibot.services.scenaries.implemetations.wikitabia.FilteredResourceScenario;
import com.intabia.wikibot.services.scenaries.implemetations.wikitabia.MainButtonScenario;
import com.intabia.wikibot.services.scenaries.implemetations.wikitabia.ResourcePageButtonScenario;
import com.intabia.wikibot.services.scenaries.implemetations.wikitabia.StartScenario;
import com.intabia.wikibot.services.scenaries.implemetations.wikitabia.TagsPageButtonScenario;

@Component
@AllArgsConstructor
public class ScenarioServiceImpl implements ScenarioService {
  private final ScenarioContainer scenarioContainer;

  private final TagsPageButtonScenario tagsPageButtonScenario;
  private final CreateResourceScenario createResourceScenario;
  private final MainButtonScenario mainButtonScenario;
  private final ResourcePageButtonScenario resourcePageButtonScenario;
  private final FilteredResourceScenario filteredResourceScenario;
  private final StartScenario startScenario;

  public void addScenarios() {

    scenarioContainer.addScenario(tagsPageButtonScenario)
        .addScenario(createResourceScenario)
        .addScenario(mainButtonScenario)
        .addScenario(resourcePageButtonScenario)
        .addScenario(filteredResourceScenario)
        .addScenario(startScenario);
  }
}
