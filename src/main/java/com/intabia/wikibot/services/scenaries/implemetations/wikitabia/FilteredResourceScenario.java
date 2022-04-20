package com.intabia.wikibot.services.scenaries.implemetations.wikitabia;

import java.util.List;

import com.intabia.wikibot.dto.telegram.UpdateDto;
import com.intabia.wikibot.dto.wikitabia.ResourceDto;
import com.intabia.wikibot.dto.wikitabia.TagDto;
import com.intabia.wikibot.mappers.JsonMapper;
import com.intabia.wikibot.services.httpsenders.HttpMethods;
import com.intabia.wikibot.services.httpsenders.abstractions.TelegramInteraction;
import com.intabia.wikibot.util.Util;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import com.intabia.wikibot.services.httpsenders.abstractions.ServerInteraction;
import com.intabia.wikibot.services.scenaries.abstractions.Scenario;

@Component
@AllArgsConstructor
public class FilteredResourceScenario implements Scenario {
  private ServerInteraction serverInteraction;
  private TelegramInteraction telegramInteraction;

  @Override
  public void doScenario(UpdateDto update, String botToken) {
    String tagName = Util.getTextFromMessage(update).replaceAll("фильтр по тегу", "");
    String json = serverInteraction.sendObjectToServer(botToken, Util.getChatId(update), new TagDto(tagName),
        "http://localhost:8080/wikitabia/api/telegram/resource/filter-by-tag/", HttpMethods.POST);
    List<ResourceDto> resourcesDto = JsonMapper.jsonToListOfObjects(json, ResourceDto[].class);
    String messageToUser = Util.convertObjectsToReadableString(resourcesDto);
    telegramInteraction.sendMessageToUser(botToken, Util.getChatId(update),
        messageToUser, null);
  }

  @Override
  public String getInvokeMessage() {
    return "фильтр по тегу";
  }
}
