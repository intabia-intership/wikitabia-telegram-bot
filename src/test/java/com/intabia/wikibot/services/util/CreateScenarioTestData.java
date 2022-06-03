package com.intabia.wikibot.services.util;

import com.intabia.wikibot.dto.telegram.ChatDto;
import com.intabia.wikibot.dto.telegram.MessageDto;
import com.intabia.wikibot.dto.telegram.UpdateDto;
import com.intabia.wikibot.dto.wikitabia.ResourceDto;
import com.intabia.wikibot.dto.wikitabia.TagDto;
import com.intabia.wikibot.dto.wikitabia.UserDto;
import com.intabia.wikibot.services.scenaries.implemetations.wikitabia.FilteredResourceScenario;
import java.util.List;
import java.util.UUID;

public interface CreateScenarioTestData {

  TagDto goodTag = TagDto.builder()
      .name("Docker")
      .build();

  TagDto badTag = TagDto.builder()
      .name("ERROR404")
      .build();

  UserDto goodUser = UserDto.builder()
      .firstName("Василий")
      .lastName("Лусников")
      .login("v.lusnikov")
      .build();

  ResourceDto goodResource = ResourceDto.builder()
      .name("Guide docker compose with docker v2.0")
      .id(UUID.randomUUID())
      .creator(goodUser)
      .url("https://docs.docker.com/compose/")
      .tags(List.of(goodTag))
      .build();

  ChatDto goodChat = ChatDto.builder()
      .chatId("ID")
      .build();
  MessageDto messageFilterGoodTag = MessageDto.builder()
      .text(String.format("%s%s", FilteredResourceScenario.INVOKE_MESSAGE, goodTag.getName()))
      .chat(goodChat)
      .build();

  MessageDto messageFilterBadTag = MessageDto.builder()
      .text(String.format("%s%s", FilteredResourceScenario.INVOKE_MESSAGE, badTag.getName()))
      .chat(goodChat)
      .build();

  UpdateDto filterGoodUpdate = UpdateDto.builder()
      .message(messageFilterGoodTag)
      .build();

  UpdateDto filterBadUpdate = UpdateDto.builder()
      .message(messageFilterBadTag)
      .build();

  List<ResourceDto> answerOnGoodTag = List.of(goodResource);
}
