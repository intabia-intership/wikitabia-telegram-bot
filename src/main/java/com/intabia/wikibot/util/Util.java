package com.intabia.wikibot.util;

import java.lang.reflect.Field;
import java.util.List;
import com.intabia.wikibot.dto.ReadableForUsers;
import com.intabia.wikibot.dto.telegram.CallbackQueryDto;
import com.intabia.wikibot.dto.telegram.UpdateDto;
import com.intabia.wikibot.exceptions.NullArgumentException;
import com.intabia.wikibot.exceptions.ReflectionException;
import com.intabia.wikibot.services.scenaries.implemetations.inner.Button;

public class Util {

  public static  <T> T setFieldToObject(T dtoObject, String dtoFieldName, String value) {
    try {
      Field field = dtoObject.getClass().getDeclaredField(dtoFieldName);
      field.setAccessible(true);
      field.set(dtoObject, value);
      return dtoObject;
    } catch (Exception e) {
      throw new ReflectionException("Ошибка вставки значения в поле", e);
    }
  }

  public static <T> T createObject(Class<T> dtoClass) {
    try {
      return dtoClass.getConstructor().newInstance();
    } catch (Exception e) {
      throw new ReflectionException("Ошибка создания объекта", e);
    }
  }

  public static String getTextFromMessage(UpdateDto update) {
    String textFromMessage;
    if (update.getMessage() == null && update.getCallbackFromButtonPress() != null) {
      textFromMessage = update.getCallbackFromButtonPress().getData();
    } else {
      textFromMessage = update.getMessage().getText();
    }
    return textFromMessage;
  }

  public static String getChatId(UpdateDto update) {
    String chatId;
    if (update.getMessage() != null) {
      chatId = update.getMessage().getChat().getChatId();
    } else {
      chatId = update.getCallbackFromButtonPress().getMessage().getChat().getChatId();
    }
    return chatId;
  }

  public static String getTelegramUsername(UpdateDto update) {
    return update.getMessage().getUser().getUserName();
  }

  public static String getButtonName(UpdateDto update) {
    String textFromMessage = getTextFromMessage(update);
    CallbackQueryDto callbackQuery = update.getCallbackFromButtonPress();
    if (callbackQuery == null) {
      return null;
    }
    for (Button[] buttonsArray: callbackQuery.getMessage().getKeyboardMarkup().getKeyboardButtons()){
      for (Button button: buttonsArray) {
        if (button.getCallbackData().equals(textFromMessage)) {
          return button.getText();
        }
      }
    }
    return null;
  }

  public static  <T extends ReadableForUsers> String convertObjectsToReadableString(
      List<T> listOfDtoClasses) {
    if (listOfDtoClasses == null) {
      throw new NullArgumentException("Аргумент - null");
    }
    StringBuilder finalReadableString = new StringBuilder();
    for (T dto : listOfDtoClasses) {
      finalReadableString.append(dto.toReadableString());
    }
    return finalReadableString.toString();
  }
}
