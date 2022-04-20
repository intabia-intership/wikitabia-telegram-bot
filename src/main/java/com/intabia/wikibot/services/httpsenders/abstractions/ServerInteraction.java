package com.intabia.wikibot.services.httpsenders.abstractions;

import java.util.List;

import com.intabia.wikibot.dto.ReadableForUsers;
import com.intabia.wikibot.services.httpsenders.HttpMethods;

public interface ServerInteraction {
  <T extends ReadableForUsers> String sendObjectToServer(String botToken, String chatId,
                                                         T object, String endPoint,
                                                         HttpMethods httpMethod);

  <T extends ReadableForUsers> List<T> getContentFromServer(HttpMethods httpMethod,
                                                            String endPoint, Class<T[]> dtoClasses);
}
