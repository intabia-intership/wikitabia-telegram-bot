package com.intabia.wikibot.services.scenaries.implemetations.wikitabia;

import com.intabia.wikibot.dto.telegram.UpdateDto;
import com.intabia.wikibot.services.httpsenders.abstractions.TelegramInteraction;
import com.intabia.wikibot.services.scenaries.implemetations.inner.Button;
import com.intabia.wikibot.services.scenaries.implemetations.inner.ButtonsMarkup;
import com.intabia.wikibot.util.Util;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import com.intabia.wikibot.services.scenaries.abstractions.Scenario;

@Component
@AllArgsConstructor
public class MainButtonScenario implements Scenario {
  private final TelegramInteraction telegramInteraction;

  @Override
  public void doScenario(UpdateDto update, String botToken) {
    Button[][] buttons = new Button[][] {new Button[] {
        new Button("Ресурсы", "ресурсы"),
        new Button("Теги", "теги")},
        {new Button("Добавить ресурс", "создать ресурс")},
        {new Button("кнопки", "кнопки")}};
    telegramInteraction.sendMessageToUser(botToken, Util.getChatId(update),
        "кнопки", new ButtonsMarkup(buttons));
  }

  @Override
  public String getInvokeMessage() {
    return "кнопки";
  }
}
