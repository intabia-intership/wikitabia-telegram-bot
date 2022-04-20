package com.intabia.wikibot.datasavers;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import java.util.concurrent.TimeUnit;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
public class ChatDataContainer {
  @Getter
  private final Cache<String, ChatData> chatDataCache;

  private ChatDataContainer() {
    this.chatDataCache = Caffeine.newBuilder()
        .expireAfterAccess(3, TimeUnit.MINUTES)
        .maximumSize(100)
        .build();
  }

  public void addOrUpdateChatData(String chatId, ChatData data) {
    synchronized (ChatDataContainer.class) {
      chatDataCache.put(chatId, data);
    }
  }

  public ChatData getChatData(String chatId) {
    return chatDataCache.getIfPresent(chatId);
  }
}
