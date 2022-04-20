package com.intabia.wikibot.services.httpsenders;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import com.intabia.wikibot.exceptions.ConnectionException;
import com.intabia.wikibot.exceptions.HttpCreationException;
import com.intabia.wikibot.exceptions.NullArgumentException;
import com.intabia.wikibot.mappers.JsonMapper;
import com.intabia.wikibot.services.httpsenders.abstractions.TelegramInteraction;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.stereotype.Component;
import com.intabia.wikibot.services.scenaries.implemetations.inner.ButtonsMarkup;

/**
 * Класс для отправки request запросов на какой-либо сервер.
 */
@Component
public class TelegramInteractionImpl implements TelegramInteraction {

  public void sendMessageToUser(String botToken, String chatId, String message, ButtonsMarkup buttonsMarkup) {
    if (botToken == null || chatId == null) {
      throw new NullArgumentException("botToken и chatId не могут быть null");
    }
    HttpRequestBase http = createHttpForMessage(botToken, chatId, message, buttonsMarkup);
    try (CloseableHttpClient client = HttpClients.createDefault();
         CloseableHttpResponse response = client.execute(http)) {
      HttpEntity httpEntity = response.getEntity();
      if (httpEntity == null) {
        throw new ConnectionException("Ошибка подключения к серверу telegram");
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private HttpPost createHttpForMessage(String botToken, String chatId, String message, ButtonsMarkup buttons) {
    String urlForMessageSending = "https://api.telegram.org/bot" + botToken + "/sendMessage";
    List<NameValuePair> params = new ArrayList<>();
    params.add(new BasicNameValuePair("chat_id", chatId));
    params.add(new BasicNameValuePair("text", message));
    if (buttons != null) {
      String json = JsonMapper.objectToJson(buttons);
      params.add(new BasicNameValuePair("reply_markup", json));
    }
    HttpPost httpPost = new HttpPost(urlForMessageSending);
    try {
      httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    return httpPost;
  }

  @Override
  public void sendSomeContentToUser(String botToken, String chatId, String animId, ContentType contentType) {
    if (botToken == null || chatId == null) {
      throw new NullArgumentException("botToken и chatId не могут быть null");
    }
    HttpRequestBase http = createHttpForContent(botToken, chatId, animId, contentType);
    try (CloseableHttpClient client = HttpClients.createDefault();
         CloseableHttpResponse response = client.execute(http)) {
      HttpEntity httpEntity = response.getEntity();
      if (httpEntity == null) {
        throw new ConnectionException("Ошибка подключения к серверу telegram");
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private HttpPost createHttpForContent(String botToken, String chatId, String contentId, ContentType contentType) {
    String url = "https://api.telegram.org/bot" + botToken;
    List<NameValuePair> params = new ArrayList<>();
    params.add(new BasicNameValuePair("chat_id", chatId));
    url = addContent(params, url, contentType, contentId);
    HttpPost httpPost = new HttpPost(url);
    try {
      httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    return httpPost;
  }

  private String addContent(List<NameValuePair> params, String url, ContentType contentType, String contentId) {
    switch (contentType) {
      case AUDIO:
        params.add(new BasicNameValuePair("audio", contentId));
        return url + "/sendAudio";
      case ANIMATION:
        params.add(new BasicNameValuePair("animation", contentId));
        return url + "/sendAnimation";
      case PHOTO:
        params.add(new BasicNameValuePair("photo", contentId));
        return url + "/sendPhoto";
      case VIDEO:
        params.add(new BasicNameValuePair("video", contentId));
        return url + "/sendVideo";
      case DOCUMENT:
        params.add(new BasicNameValuePair("document", contentId));
        return url +"/sendDocument";
      default:
        throw new HttpCreationException("Невозможно отправить контент");
    }
  }
}