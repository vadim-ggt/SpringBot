package com.Bot5wProj.SpringBotTGmy.service;


import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class MessageSender {

    private static final Logger logger = LoggerFactory.getLogger(MessageSender.class);

    private final TelegramBot telegramBot;

    public MessageSender( @Lazy TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

        public void sendMessage(Long chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(text);

        try {
            telegramBot.execute(message);
        } catch (TelegramApiException e) {
            logger.error("Ошибка при отправке сообщения пользователю с ID {}: {}", chatId, e.getMessage(), e);
        }
    }
}
