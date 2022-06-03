package com.intabia.wikibot.dto.wikitabia;

import java.util.List;
import java.util.UUID;

import com.intabia.wikibot.dto.ReadableForUsers;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * dto класс для передачи сущности resources между frontend и backend.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResourceDto implements ReadableForUsers {
  /**
   * Идентификатор ресурса.
   */
  private UUID id;
  /**
   * Имя ресурса.
   */
  private String name;
  /**
   * URL адрес ресурса.
   */
  private String url;
  /**
   * создатель
   */
  private UserDto creator;
  /**
   * Список тегов.
   */
  private List<TagDto> tags;

  @Override
  public String toReadableString() {
    return "название: " + name +
        "   ссылка: " + url + "\n";
  }
}
