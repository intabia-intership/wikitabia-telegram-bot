package com.intabia.wikibot.services.httpsenders.abstractions;

import com.intabia.wikibot.services.httpsenders.ContentType;
import com.intabia.wikibot.services.scenaries.implemetations.inner.ButtonsMarkup;

public interface TelegramInteraction {
  void sendMessageToUser(String botToken, String chatId, String message,
                         ButtonsMarkup buttonsMarkup);

  void sendSomeContentToUser(String botToken, String chatId, String animId, ContentType contentType);
}
