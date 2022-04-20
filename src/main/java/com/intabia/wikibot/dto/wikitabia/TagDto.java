package com.intabia.wikibot.dto.wikitabia;

import java.util.UUID;

import com.intabia.wikibot.dto.ReadableForUsers;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * dto класс для передачи сущности tags между frontend и backend.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TagDto implements ReadableForUsers {
  /**
   * id тега.
   */
  private UUID id;
  /**
   * название тега.
   */
  private String name;

  public TagDto(String name) {
    this.name = name;
  }

  @Override
  public String toReadableString() {
    return name + "\n";
  }
}
