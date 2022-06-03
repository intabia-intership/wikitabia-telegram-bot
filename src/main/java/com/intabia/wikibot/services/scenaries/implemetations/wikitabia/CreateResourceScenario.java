package com.intabia.wikibot.services.scenaries.implemetations.wikitabia;

import com.intabia.wikibot.datasavers.ChatScenarioChain;
import com.intabia.wikibot.datasavers.ChatScenarioChainContainer;
import com.intabia.wikibot.datasavers.Step;
import com.intabia.wikibot.dto.telegram.UpdateDto;
import com.intabia.wikibot.dto.wikitabia.ResourceDto;
import com.intabia.wikibot.dto.wikitabia.TagDto;
import com.intabia.wikibot.dto.wikitabia.UserDto;
import com.intabia.wikibot.integration.client.WikitabiaClient;
import com.intabia.wikibot.services.httpsenders.abstractions.TelegramInteraction;
import com.intabia.wikibot.services.scenaries.abstractions.Scenario;
import com.intabia.wikibot.util.Util;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CreateResourceScenario implements Scenario {
  private final ChatScenarioChainContainer chainContainer;
  private final TelegramInteraction telegramInteraction;
  private WikitabiaClient wikitabiaClient;


  @Override
  public void doScenario(UpdateDto update, String botToken) {
    String chatId = Util.getChatId(update);
    ChatScenarioChain<?> scenarioChain = chainContainer.getScenarioChainCache()
        .getIfPresent(chatId);
    if (scenarioChain == null) {
      chainContainer.addOrUpdate(chatId, new ChatScenarioChain<>(this, Step.FIRST, new ResourceDto()));
      scenarioChain = chainContainer.getScenarioChainCache().getIfPresent(chatId);
    }
    assert scenarioChain != null;
    ResourceDto dto = (ResourceDto) scenarioChain.getDto();
    switch (scenarioChain.getStep()) {
      case FIRST:
        firstStep(chatId, botToken, scenarioChain);
        break;
      case SECOND:
        secondStep(dto, update, chatId, botToken, scenarioChain);
        break;
      case THIRD:
        thirdStep(dto, update, chatId, botToken, scenarioChain);
        break;
      case FOURTH:
        fourthStep(dto, update, chatId, botToken);
    }
  }

  private void firstStep(String chatId, String botToken, ChatScenarioChain<?> scenarioChain) {
    telegramInteraction.sendMessageToUser(botToken, chatId,
        "Название:", null);
    scenarioChain.setNextScenario(this);
    scenarioChain.setStep(Step.SECOND);
  }

  private void secondStep(ResourceDto dto, UpdateDto update, String chatId,
                          String botToken, ChatScenarioChain<?> scenarioChain) {
    dto.setName(Util.getTextFromMessage(update));
    telegramInteraction.sendMessageToUser(botToken, chatId,
        "Url:", null);
    scenarioChain.setStep(Step.THIRD);
  }

  private void thirdStep(ResourceDto dto, UpdateDto update, String chatId,
                         String botToken, ChatScenarioChain<?> scenarioChain) {
    dto.setUrl(Util.getTextFromMessage(update));
    telegramInteraction.sendMessageToUser(botToken, chatId,
        "Теги, через запятую:", null);
    scenarioChain.setStep(Step.FOURTH);
  }

  private void fourthStep(ResourceDto dto, UpdateDto update, String chatId, String botToken) {
    List<TagDto> tags = reformatMessageToTags(Util.getTextFromMessage(update));
    dto.setTags(tags);
    dto.setCreator(new UserDto(Util.getTelegramUsername(update)));
    wikitabiaClient.createResourceByTelegram(dto);
    telegramInteraction.sendMessageToUser(botToken, chatId,
        "Добавлен!", null);
    chainContainer.getScenarioChainCache().invalidate(chatId);
  }

  private List<TagDto> reformatMessageToTags(String messageFromUser) {
    List<TagDto> tagDto = null;
    if (!messageFromUser.equals("")) {
      String[] parsedTags = messageFromUser.replaceAll("\\s+", "").toLowerCase().split(",");
      tagDto = Arrays.stream(parsedTags)
          .map(TagDto::new)
          .collect(Collectors.toList());
    }
    return tagDto;
  }

  @Override
  public String getInvokeMessage() {
    return "создать ресурс";
  }
}
