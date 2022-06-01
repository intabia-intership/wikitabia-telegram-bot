package com.intabia.wikibot.services.scenaries.implemetations.wikitabia;

import com.intabia.wikibot.datasavers.ChatData;
import com.intabia.wikibot.datasavers.ChatDataContainer;
import com.intabia.wikibot.dto.telegram.UpdateDto;
import com.intabia.wikibot.dto.wikitabia.TagDto;
import com.intabia.wikibot.integration.client.WikitabiaClient;
import com.intabia.wikibot.services.httpsenders.abstractions.TelegramInteraction;
import com.intabia.wikibot.services.scenaries.abstractions.Scenario;
import com.intabia.wikibot.services.scenaries.implemetations.inner.Button;
import com.intabia.wikibot.services.scenaries.implemetations.inner.ButtonsMarkup;
import com.intabia.wikibot.util.Util;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class TagsPageButtonScenario implements Scenario {
  private final TelegramInteraction telegramInteraction;
  private final ChatDataContainer chatDataContainer;
  private final WikitabiaClient wikitabiaClient;

  @Override
  public void doScenario(UpdateDto update, String botToken) {
    int pageNumber = getPageNumberFromChatData(Util.getChatId(update));
    String messageFromUser = Util.getTextFromMessage(update);
    if ("теги+".equals(messageFromUser)) {
      ++pageNumber;
    } else if ("теги-".equals(messageFromUser)){
      --pageNumber;
    }
    List<TagDto> tags = wikitabiaClient.getTagsPage(pageNumber);

    if (tags == null || tags.isEmpty()) {
      if (pageNumber < 0) {
        ++pageNumber;
      } else {
        --pageNumber;
      }
      return;
    }
    ButtonsMarkup buttonsMarkup = buildButtonsMarkup(tags);
    telegramInteraction.sendMessageToUser(botToken, Util.getChatId(update), "Поиск ресурса по тегу",
        buttonsMarkup);
    chatDataContainer.addOrUpdateChatData(Util.getChatId(update),
        new ChatData(pageNumber));
  }

  private int getPageNumberFromChatData(String chatId) {
    ChatData chatData = chatDataContainer.getChatData(chatId);
    if (chatData == null) {
      return 0;
    }
    return chatData.getTagPageNumber();
  }

  private ButtonsMarkup buildButtonsMarkup(List<TagDto> tags) {
    Button[][] buttons = new Button[(int) Math.ceil(tags.size()/3.0) + 1][3];
    int firstIndex = 0;
    int secondIndex = 0;
    for (TagDto tag: tags) {
      buttons[firstIndex][secondIndex] = new Button(tag.getName(), "фильтр по тегу" + tag.getName());
      ++secondIndex;
      if (secondIndex > 2) {
        ++firstIndex;
        secondIndex = 0;
      }
    }
    buttons[firstIndex] = new Button[]{(new Button("Назад", "теги-")),
        new Button("Еще", "теги+")};
    return new ButtonsMarkup(buttons);
  }

  @Override
  public String getInvokeMessage() {
    return "теги";
  }
}
