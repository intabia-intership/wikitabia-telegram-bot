package com.intabia.wikibot.datasavers;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import java.util.concurrent.TimeUnit;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Component
public class ChatScenarioChainContainer {
  private final Cache<String, ChatScenarioChain<?>> scenarioChainCache;

  private ChatScenarioChainContainer() {
    this.scenarioChainCache = Caffeine.newBuilder()
        .expireAfterAccess(1, TimeUnit.MINUTES)
        .maximumSize(100)
        .build();
  }

  public void addOrUpdate(String chatId, ChatScenarioChain<?> scenarioChain) {
    synchronized (ChatScenarioChainContainer.class) {
      scenarioChainCache.put(chatId, scenarioChain);
    }
  }
}
