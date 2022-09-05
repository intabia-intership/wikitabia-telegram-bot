package com.intabia.wikibot.services.scenaries.implemetations.wikitabia;

import com.intabia.wikibot.client.SendFileSto;
import com.intabia.wikibot.client.TelegramClient;
import java.util.List;

import com.intabia.wikibot.datasavers.ChatData;
import com.intabia.wikibot.datasavers.ChatDataContainer;
import com.intabia.wikibot.dto.telegram.UpdateDto;
import com.intabia.wikibot.dto.wikitabia.ResourceDto;
import com.intabia.wikibot.services.httpsenders.HttpMethods;
import com.intabia.wikibot.services.httpsenders.abstractions.TelegramInteraction;
import com.intabia.wikibot.util.Util;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import com.intabia.wikibot.services.httpsenders.abstractions.ServerInteraction;
import com.intabia.wikibot.services.scenaries.abstractions.Scenario;
import com.intabia.wikibot.services.scenaries.implemetations.inner.Button;
import com.intabia.wikibot.services.scenaries.implemetations.inner.ButtonsMarkup;

@Component
@AllArgsConstructor
public class ResourcePageButtonScenario implements Scenario {
  private ChatDataContainer chatDataContainer;
  private final TelegramClient telegramClient;

  @Override
  public void doScenario(UpdateDto update, String botToken) {
    telegramClient.sendFileById(SendFileSto.builder()
            .chatId(Util.getChatId(update))
            .document("CQACAgIAAxkDAAMuYxVlpxDIGoUePw4vg71bE-YldEcAAooiAAK8OalIvTtQq41ExDwpBA")
            .build());
  }

  private long getPageNumberFromChatData(String chatId) {
    ChatData chatData = chatDataContainer.getChatData(chatId);
    if (chatData == null) {
      return 0;
    }
    return chatData.getResourcePageNumber();
  }

  @Override
  public String getInvokeMessage() {
    return "песня";
  }
}
