package com.Bot5wProj.SpringBotTGmy.service;

import org.springframework.stereotype.Service;

@Service
public class BotCommandHandler {

    private final MessageSender messageSender;

    public BotCommandHandler(MessageSender messageSender) {
         this.messageSender = messageSender;
    }

    public void handleCommand(Long chatId, String command) {
        switch (command){
            case "/start" : {
                messageSender.sendMessage(chatId, "Привет! Я бот для мониторинга цен.");
                break;
            }
            case "/help":{
                messageSender.sendMessage(chatId, "Список команд: /start, /track, /list");
                break;
            }
            case "/track":{
                messageSender.sendMessage(chatId, "Отправте ссылку на товар с Wildberries");
                break;
            }
            default:{messageSender.sendMessage(chatId, "Неизвестная комманда. Используйте /help");}
        }

    }
}
