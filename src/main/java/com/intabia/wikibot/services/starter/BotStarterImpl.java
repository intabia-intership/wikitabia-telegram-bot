package com.intabia.wikibot.services.starter;

import com.intabia.wikibot.client.TelegramClient;
import com.intabia.wikibot.datasavers.ChatScenarioChain;
import com.intabia.wikibot.datasavers.ChatScenarioChainContainer;
import com.intabia.wikibot.dto.telegram.UpdateDto;
import com.intabia.wikibot.exceptions.EmptyPatternContainerException;
import com.intabia.wikibot.services.httpsenders.abstractions.TelegramInteraction;
import com.intabia.wikibot.services.httpsenders.abstractions.TelegramRequestUpdater;
import com.intabia.wikibot.services.scenaries.abstractions.Scenario;
import com.intabia.wikibot.util.Util;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import com.intabia.wikibot.services.scenaries.abstractions.ScenarioContainer;
import com.intabia.wikibot.services.scenaries.abstractions.ScenarioDispatcher;
import com.intabia.wikibot.services.scenaries.abstractions.ScenarioService;

@Component
@AllArgsConstructor
public class BotStarterImpl implements BotStarter {
    private final TelegramRequestUpdater requestsUpdater;
    private final ScenarioDispatcher scenarioDispatcher;
    private final ScenarioContainer scenarioContainerImpl;
    private final ChatScenarioChainContainer chainContainer;
    private final ScenarioService scenarioServiceImpl;
    private final TelegramInteraction telegramInteraction;
    private final TelegramClient telegramClient;

    public void startBot(String botToken) {
        scenarioServiceImpl.addScenarios();

        if (scenarioContainerImpl.getContainer().isEmpty()) {
            throw new EmptyPatternContainerException("PatternContainer пуст");
        }
        UpdateDto update = requestsUpdater.getUpdate(botToken);
        if (update == null) {
            return;
        }
        ChatScenarioChain<?> scenarioChain = chainContainer.getScenarioChainCache()
                .getIfPresent(Util.getChatId(update));
        if (scenarioChain != null) {
            scenarioChain.getNextScenario().doScenario(update, botToken);
            return;
        }
        String textFromMessage = Util.getTextFromMessage(update);
        Scenario scenario = scenarioDispatcher.findScenario(textFromMessage.toLowerCase());
        if (scenario == null) {
            telegramInteraction.sendMessageToUser(botToken, Util.getChatId(update),
                    "команда не найдена", null);
            return;
        }
        scenario.doScenario(update, botToken);
    }
}

