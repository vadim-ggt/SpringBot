package com.Bot5wProj.SpringBotTGmy.service;


import com.Bot5wProj.SpringBotTGmy.config.BotConfig;
import com.Bot5wProj.SpringBotTGmy.controller.BotController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class TelegramBot extends TelegramLongPollingBot {

    private final BotConfig botConfig;
    private final BotController botController;

    @Autowired
    public TelegramBot(BotConfig botConfig, BotController botController) {
        this.botConfig = botConfig;
        this.botController = botController;
    }

    @Override
    public void onUpdateReceived(Update update) {
        botController.handleUpdate(update);
    }

    @Override
    public String getBotUsername() {
        return botConfig.getBotName();
    }


    @Override
    public String getBotToken() {
        return botConfig.getToken();
    }


    private void sendMessage(long chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(text);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

}
