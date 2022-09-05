package com.intabia.wikibot.services.scenaries.implemetations.wikitabia;

import com.intabia.wikibot.client.SendFileSto;
import com.intabia.wikibot.client.TelegramClient;
import com.intabia.wikibot.dto.telegram.UpdateDto;
import com.intabia.wikibot.services.httpsenders.abstractions.TelegramInteraction;
import com.intabia.wikibot.services.scenaries.implemetations.inner.Button;
import com.intabia.wikibot.services.scenaries.implemetations.inner.ButtonsMarkup;
import com.intabia.wikibot.util.Util;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import com.intabia.wikibot.services.scenaries.abstractions.Scenario;

@Component
@AllArgsConstructor
public class MainButtonScenario implements Scenario {
  private final TelegramInteraction telegramInteraction;
  private final TelegramClient telegramClient;

  @Override
  @SneakyThrows
  public void doScenario(UpdateDto update, String botToken) {
    Button[][] buttons = new Button[][] {new Button[] {
        new Button("Добавить песню", "ресурсы"),
        new Button("Получить песню", "песня")},
    };

    telegramInteraction.sendMessageToUser(botToken, Util.getChatId(update),
        "Доброе утро!", new ButtonsMarkup(buttons));

    telegramClient.sendFileById(SendFileSto.builder()
            .chatId(Util.getChatId(update))
            .document("CQACAgIAAxkDAAMuYxVlpxDIGoUePw4vg71bE-YldEcAAooiAAK8OalIvTtQq41ExDwpBA")
            .build());
  }

  @Override
  public String getInvokeMessage() {
    return "кнопки";
  }
}
