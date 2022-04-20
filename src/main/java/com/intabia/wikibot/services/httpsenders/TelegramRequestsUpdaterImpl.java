package com.intabia.wikibot.services.httpsenders;

import java.io.IOException;
import java.nio.charset.Charset;

import com.intabia.wikibot.dto.telegram.ResultDto;
import com.intabia.wikibot.dto.telegram.UpdateDto;
import com.intabia.wikibot.exceptions.ConnectionException;
import com.intabia.wikibot.mappers.JsonMapper;
import com.intabia.wikibot.services.httpsenders.abstractions.TelegramRequestUpdater;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Component;

/**
 * Класс для опроса сервера телеграм на предмет обновлений от пользователей.
 */
@Component
public class TelegramRequestsUpdaterImpl implements TelegramRequestUpdater {

  private static long updateId;
  /**
   * метод запроса в телеграм для получения обновлений.
   * @param botToken уникальный токен бота.
   * @return UpdateDto - объект ответа телеграм.
   */
  public UpdateDto getUpdate(String botToken) {
    String responseContent = sendRequestToTelegram(botToken);
    System.out.println(responseContent);
    if (responseContent == null) {
      return null;
    }
    ResultDto result = JsonMapper.jsonToObject(responseContent, ResultDto.class);
    if (result == null || !result.isOk() || result.getUpdates().isEmpty()) {
      return null;
    }
    updateId = result.getUpdates().get(0).getUpdateId() + 1;
    return result.getUpdates().get(0);
  }

  private String sendRequestToTelegram(String botToken) {
    String getUpdatesUrl = "https://api.telegram.org/bot" + botToken + "/getUpdates";
    if (updateId != 0) {
      getUpdatesUrl = getUpdatesUrl + "?offset=" + updateId;
    }
    try (CloseableHttpClient client = HttpClients.createDefault();
         CloseableHttpResponse response = client.execute(new HttpGet(getUpdatesUrl))) {
      HttpEntity httpEntity = response.getEntity();
      if (httpEntity == null) {
        throw new ConnectionException("Ошибка подключения к серверу telegram");
      }
      return IOUtils.toString(httpEntity.getContent(), Charset.defaultCharset());
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }
}