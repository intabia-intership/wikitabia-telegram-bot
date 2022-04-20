package com.intabia.wikibot.scheduler;

import com.intabia.wikibot.services.starter.BotStarter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Slf4j
public class BotStartScheduler {
    @NonNull
    private final BotStarter botStarter;
    @Value("${application.token}")
    private String botToken;

    @Scheduled(fixedRateString = "${application.scheduler.rate}")
    public void startBotScheduler() throws IOException {
        log.info("telegram updates request");

        botStarter.startBot(botToken);
    }
}
