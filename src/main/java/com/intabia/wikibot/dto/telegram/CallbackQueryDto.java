package com.intabia.wikibot.dto.telegram;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CallbackQueryDto {
  private String id;
  @JsonProperty("from")
  private UserDto user;
  private MessageDto message;
  private String data;
}
