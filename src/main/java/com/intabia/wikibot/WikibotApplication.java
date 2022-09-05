package com.intabia.wikibot;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableFeignClients
@EnableScheduling
public class WikibotApplication {
  public static void main(String[] args) {
    new SpringApplicationBuilder(WikibotApplication.class).build().run(args);
  }
}