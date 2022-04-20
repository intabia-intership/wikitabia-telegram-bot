package com.intabia.wikibot.services.scenaries.implemetations.wikitabia;

import com.intabia.wikibot.dto.telegram.UpdateDto;
import com.intabia.wikibot.services.httpsenders.abstractions.TelegramInteraction;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import com.intabia.wikibot.services.scenaries.abstractions.Scenario;

@Component
@RequiredArgsConstructor
@PropertySource("classpath:application.yml")
public class StartScenario implements Scenario {
  private final MainButtonScenario mainButtonScenario;
  private final TelegramInteraction telegramInteraction;
  private final Environment env;

  @Override
  public void doScenario(UpdateDto update, String botToken) {
    mainButtonScenario.doScenario(update, botToken);
  }

  @Override
  public String getInvokeMessage() {
    return "/start";
  }
}
