package com.intabia.wikibot.dto.telegram;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateDto {
  @JsonProperty("update_id")
  private long updateId;

  private MessageDto message;

  @JsonProperty("callback_query")
  private CallbackQueryDto callbackFromButtonPress;
}
