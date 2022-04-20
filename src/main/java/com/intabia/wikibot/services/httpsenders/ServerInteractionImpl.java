package com.intabia.wikibot.services.httpsenders;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.List;

import com.intabia.wikibot.dto.ReadableForUsers;
import com.intabia.wikibot.exceptions.HttpCreationException;
import com.intabia.wikibot.exceptions.HttpMethodNotFoundException;
import com.intabia.wikibot.exceptions.NullArgumentException;
import com.intabia.wikibot.mappers.JsonMapper;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Component;
import com.intabia.wikibot.services.httpsenders.abstractions.ServerInteraction;

/**
 * Класс для отправки сообщений или json.
 */
@Component
public class ServerInteractionImpl implements ServerInteraction {
  /**
   * Метод для запроса json на каком либо сервере.
   * @param httpMethod http method.
   * @param endPoint url.
   * @param dtoClasses параметр для маппинга.
   * @param <T> ограничение для dto.
   * @return
   * @throws IOException
   */
  public <T extends ReadableForUsers> List<T> getContentFromServer(HttpMethods httpMethod,
                                                                   String endPoint, Class<T[]> dtoClasses) {
    String responseFromServer = sendRequestToServer(httpMethod, endPoint);
    return JsonMapper.jsonToListOfObjects(responseFromServer, dtoClasses);
  }

  private String sendRequestToServer(HttpMethods httpMethods, String endPoint) {
    HttpRequestBase http = getHttpMethod(httpMethods, endPoint);
    try (CloseableHttpClient client = HttpClients.createDefault();
         CloseableHttpResponse response = client.execute(http)) {
      HttpEntity httpEntity = response.getEntity();
      return IOUtils.toString(httpEntity.getContent(), Charset.defaultCharset());
    } catch (RuntimeException | IOException e) {
      e.printStackTrace();
      return null;
    }
  }

  private HttpRequestBase getHttpMethod(HttpMethods httpMethod, String endPoint) {
    switch (httpMethod) {
      case GET:
        return new HttpGet(endPoint);
      case POST:
        return new HttpPost(endPoint);
      case PUT:
        return new HttpPut(endPoint);
      case DELETE:
        return new HttpDelete(endPoint);
      default:
        throw new HttpMethodNotFoundException("http method не найден");
    }
  }
  /**
   * метод для отправки json на какой-либо сервер.
   * @param botToken уникальный токен зарегистрированного бота.
   * @param chatId id чата для навигации.
   * @param object dto для отправки.
   * @param <T> dto реализующий ReadableForUsers.
   */
  public <T extends ReadableForUsers> String sendObjectToServer(String botToken, String chatId,
                                                              T object, String endPoint,
                                                              HttpMethods httpMethod) {
    if (botToken == null || chatId == null) {
      throw new NullArgumentException("botToken и chatId не могут быть null");
    }

    HttpRequestBase http = createHttpForServer(object, endPoint, httpMethod);

    try (CloseableHttpClient client = HttpClients.createDefault();
         CloseableHttpResponse response = client.execute(http)) {
      HttpEntity httpEntity = response.getEntity();
      return IOUtils.toString(httpEntity.getContent(), Charset.defaultCharset());
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }

  private <T extends ReadableForUsers> HttpEntityEnclosingRequestBase createHttpForServer(T object, String endPoint,
                                                                           HttpMethods httpMethod) {
    HttpEntityEnclosingRequestBase http;
    if (httpMethod == HttpMethods.POST) {
      http = new HttpPost(endPoint);
    } else {
      http = new HttpPut(endPoint);
    }
    String json = JsonMapper.objectToJson(object);
    assert json != null;
    StringEntity entity;
    try {
      entity = new StringEntity(json);
    } catch (UnsupportedEncodingException e) {
      throw new HttpCreationException("ошибка создания http", e);
    }
    http.setEntity(entity);
    http.setHeader("Accept", "application/json");
    http.setHeader("Content-type", "application/json");
    return http;
  }
}