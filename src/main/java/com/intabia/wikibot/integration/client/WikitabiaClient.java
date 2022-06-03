package com.intabia.wikibot.integration.client;

import com.intabia.wikibot.dto.wikitabia.ResourceDto;
import com.intabia.wikibot.dto.wikitabia.TagDto;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "WikitabiaClient", url = "${client.url.wiki}", decode404 = true)
public interface WikitabiaClient {

  @PostMapping(value = "/telegram/resource/create",
      consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<ResourceDto> createResourceByTelegram(@RequestBody ResourceDto resource);

  @PostMapping(value = "/telegram/resource/filter-by-tag",
      consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<List<ResourceDto>> getResourcePageByTag(@RequestBody TagDto tag);

  @GetMapping("/pageable-resources/{page}")
  ResponseEntity<List<ResourceDto>> getResources(@PathVariable long page);

  @GetMapping(value = "/tag/page/{page}", produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<List<TagDto>> getTagsPage(@PathVariable long page);
}
