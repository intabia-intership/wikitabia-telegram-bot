package com.intabia.wikibot.dto.telegram;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.intabia.wikibot.services.scenaries.implemetations.inner.ButtonsMarkup;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageDto {
  @JsonProperty("message_id")
  private long messageId;

  @JsonProperty("from")
  private UserDto user;

  private ChatDto chat;

  private String text;

  @JsonProperty("reply_markup")
  private ButtonsMarkup keyboardMarkup;
}
