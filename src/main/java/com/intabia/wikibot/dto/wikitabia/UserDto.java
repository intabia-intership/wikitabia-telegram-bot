package com.intabia.wikibot.dto.wikitabia;

import java.util.UUID;

import com.intabia.wikibot.dto.ReadableForUsers;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * dto класс для передачи сущности users между frontend и backend.
 */
@Data
@NoArgsConstructor
public class UserDto implements ReadableForUsers {
  /**
   * id пользователя.
   */
  private UUID id;
  /**
   * название пользователя.
   */
  private String firstName;
  /**
   * фамилия пользователя.
   */
  private String lastName;
  /**
   * логин пользователя.
   */
  private String login;
  /**
   * пароль.
   */
  private String password;
  /**
   * логин в telegram.
   */
  private String telegramLogin;

  @Override
  public String toReadableString() {
    return "имя: " + firstName +
        "фамилия: " + lastName +
        "логин: " + login;
  }

  public UserDto(String telegramLogin) {
    this.telegramLogin = telegramLogin;
  }


}
