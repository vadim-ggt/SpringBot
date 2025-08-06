package com.Bot5wProj.SpringBotTGmy.service;


import com.Bot5wProj.SpringBotTGmy.config.BotConfig;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class TelegramBot extends TelegramLongPollingBot {

    private final BotConfig botConfig;

    public TelegramBot(BotConfig botConfig) {
        this.botConfig = botConfig;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            switch (messageText) {
                case "/start":
                    sendMessage(chatId, "Привет! Я бот для мониторинга цен.");
                    break;
                case "/help":
                    sendMessage(chatId, "Список команд: /start, /track, /list");
                    break;
                case "/track":
                    sendMessage(chatId, "Отправьте ссылку на товар с Wildberries.");
                    break;
                default:
                    sendMessage(chatId, "Неизвестная команда. Используйте /help");
            }
        }
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
