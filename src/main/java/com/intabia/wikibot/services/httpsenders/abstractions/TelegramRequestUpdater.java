package com.intabia.wikibot.services.httpsenders.abstractions;

import com.intabia.wikibot.dto.telegram.UpdateDto;

public interface TelegramRequestUpdater {
  UpdateDto getUpdate(String botToken);
}
