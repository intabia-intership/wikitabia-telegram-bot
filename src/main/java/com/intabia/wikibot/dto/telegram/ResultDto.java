package com.intabia.wikibot.dto.telegram;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultDto {
  @JsonProperty("ok")
  private boolean isOk;

  @JsonProperty("result")
  private List<UpdateDto> updates;
}
