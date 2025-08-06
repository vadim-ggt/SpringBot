package com.Bot5wProj.SpringBotTGmy.config;

import com.Bot5wProj.SpringBotTGmy.service.TelegramBot;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Configuration
public class BotInitializer {

    private final BotConfig config;
    private final TelegramBot bot;

    public BotInitializer(BotConfig config, TelegramBot bot) {
        this.config = config;
        this.bot = bot;
    }

    @Bean
    public TelegramBotsApi telegramBotsApi() throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(bot); 
        return telegramBotsApi;
    }
}

