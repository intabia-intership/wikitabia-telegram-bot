package com.intabia.wikibot.client;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import java.io.File;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;

@FeignClient(name = "telegramClient", url = "${application.url}")
public interface TelegramClient {

  @PostMapping("/sendDocument")
  Void uploadFile(@RequestPart("chat_id") String chatId, @RequestPart File document);

  @PostMapping("/sendDocument")
  Void sendFileById(@RequestBody SendFileSto sendFileSto);
}
