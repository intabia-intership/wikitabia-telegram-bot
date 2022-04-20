package com.intabia.wikibot.services.scenaries.implemetations.inner;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * класс для разметки кнопок в ответе пользователю.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ButtonsMarkup {
  /**
   * двумерный массив кнопок для конфигурации отображения в телеграм.
   */
  @JsonProperty("inline_keyboard")
  private Button[][] keyboardButtons;
}
