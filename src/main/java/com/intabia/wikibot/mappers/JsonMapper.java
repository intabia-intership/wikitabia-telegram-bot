package com.intabia.wikibot.mappers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import java.util.List;

import com.intabia.wikibot.dto.ReadableForUsers;
import com.intabia.wikibot.exceptions.MappingException;

public class JsonMapper {

  public static <T extends ReadableForUsers> List<T> jsonToListOfObjects(String json, Class<T[]> dtoClasses) {
    if (json == null || json.isBlank()) {
      return null;
    }
    try {
      ObjectMapper objectMapper = new ObjectMapper()
          .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
      return Arrays.asList(objectMapper.readValue(json, dtoClasses));
    } catch (JsonProcessingException e) {
      throw new MappingException("Ошибка преобразования json в объект", e);
    }
  }

  public static <U> U jsonToObject(String json, Class<U> dtoClass) {
    if (json == null || json.isBlank()) {
      return null;
    }
    try {
      ObjectMapper objectMapper = new ObjectMapper()
          .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
      return objectMapper.readValue(json, dtoClass);
    } catch (JsonProcessingException e) {
      throw new MappingException("Ошибка преобразования json в объект", e);
    }
  }

  public static <U> String objectToJson(U object) {
    try {
      ObjectMapper objectMapper = new ObjectMapper();
      return objectMapper.writeValueAsString(object);
    } catch (JsonProcessingException e) {
      throw new MappingException("Ошибка преобразования объекта в json", e);
    }
  }
}