package com.intabia.wikibot.datasavers;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChatData {
  private int tagPageNumber;
  private long resourcePageNumber;

  public ChatData(int tagPageNumber) {
    this.tagPageNumber = tagPageNumber;
  }

  public ChatData(long resourcePageNumber) {
    this.resourcePageNumber = resourcePageNumber;
  }
}
