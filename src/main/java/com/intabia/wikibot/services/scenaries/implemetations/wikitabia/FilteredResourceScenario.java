package com.intabia.wikibot.services.scenaries.implemetations.wikitabia;

import com.intabia.wikibot.dto.telegram.UpdateDto;
import com.intabia.wikibot.dto.wikitabia.ResourceDto;
import com.intabia.wikibot.dto.wikitabia.TagDto;
import com.intabia.wikibot.integration.client.WikitabiaClient;
import com.intabia.wikibot.services.httpsenders.abstractions.TelegramInteraction;
import com.intabia.wikibot.services.scenaries.abstractions.Scenario;
import com.intabia.wikibot.util.Util;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FilteredResourceScenario implements Scenario {

  public static final String INVOKE_MESSAGE = "фильтр по тегу";
  private final TelegramInteraction telegramInteraction;
  private final WikitabiaClient wikitabiaClient;

  @Override
  public void doScenario(UpdateDto update, String botToken) {
    String tagName = Util.getTextFromMessage(update).replaceAll("фильтр по тегу", "");
    List<ResourceDto> resourcesDto = wikitabiaClient.getResourcePageByTag(TagDto.builder()
        .name(tagName)
        .build()).getBody();
    String messageToUser = Util.convertObjectsToReadableString(resourcesDto);
    telegramInteraction.sendMessageToUser(botToken, Util.getChatId(update),
        messageToUser, null);
  }

  @Override
  public String getInvokeMessage() {
    return INVOKE_MESSAGE;
  }
}
