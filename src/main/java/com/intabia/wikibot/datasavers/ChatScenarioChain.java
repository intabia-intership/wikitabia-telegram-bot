package com.intabia.wikibot.datasavers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.intabia.wikibot.services.scenaries.abstractions.Scenario;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatScenarioChain<T> {
  private Scenario nextScenario;
  private Step step;
  private T dto;
}
