package com.Bot5wProj.SpringBotTGmy.controller;

import com.Bot5wProj.SpringBotTGmy.service.BotCommandHandler;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class BotController {

    private final BotCommandHandler commandHandler;


    public BotController(BotCommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }


    public void handleUpdate(Update update) {
        if(update.hasMessage() && update.getMessage().hasText()){
            String messageText = update.getMessage().getText();
            Long chatId = update.getMessage().getChatId();

            commandHandler.handleCommand(chatId, messageText);
        }
    }
}
