package com.intabia.wikibot.services.scenaries.implemetations.inner;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * класс для конфигурирования кнопок.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Button {
  /**
   * текст кнопки.
   */
  private String text;
  /**
   * текст, который будет отправлен из телеграм при нажатии кнопки.
   */
  @JsonProperty("callback_data")
  private String callbackData;
}
