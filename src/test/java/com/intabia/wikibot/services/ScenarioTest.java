package com.intabia.wikibot.services;

import com.intabia.wikibot.integration.client.WikitabiaClient;
import com.intabia.wikibot.services.httpsenders.abstractions.TelegramInteraction;
import com.intabia.wikibot.services.scenaries.implemetations.wikitabia.CreateResourceScenario;
import com.intabia.wikibot.services.scenaries.implemetations.wikitabia.FilteredResourceScenario;
import com.intabia.wikibot.services.util.CreateScenarioTestData;
import com.intabia.wikibot.util.Util;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class ScenarioTest implements CreateScenarioTestData {

  @Autowired
  private CreateResourceScenario createResourceScenario;

  @Autowired
  private FilteredResourceScenario filteredResourceScenario;

  @MockBean
  private WikitabiaClient wikitabiaClient;

  @MockBean
  private TelegramInteraction telegramInteraction;

  @Value("${application.token")
  String botToken;

  @BeforeEach
  void init() {
    Mockito.when(wikitabiaClient.getResourcePageByTag(goodTag)).thenReturn(ResponseEntity.ok(answerOnGoodTag));
  }

  @Test
  public void whenRunFilterScenario__WithGoodData__ThenCheckSuccessSend() {
    filteredResourceScenario.doScenario(filterGoodUpdate, botToken);
    Mockito.verify(wikitabiaClient, Mockito.times(1)).getResourcePageByTag(goodTag);
    Mockito.verify(telegramInteraction, Mockito.times(1)).sendMessageToUser(botToken, goodChat.getChatId(),
        Util.convertObjectsToReadableString(answerOnGoodTag), null);
  }
}
