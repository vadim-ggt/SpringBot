package com.Bot5wProj.SpringBotTGmy.service;

import org.telegram.telegrambots.meta.api.objects.Message;
import org.springframework.stereotype.Service;

@Service
public class BotCommandHandler {

    private final MessageSender messageSender;
    private final UserService userService;

    public BotCommandHandler(MessageSender messageSender, UserService userService) {
         this.messageSender = messageSender;
         this.userService = userService;
    }

    public void handleCommand(Message message) {
        Long chatId = message.getChatId();
        String command = message.getText();
        String username = message.getFrom().getUserName();
        switch (command){
            case "/start" : {
                userService.registerUser(chatId, username);
                messageSender.sendMessage(chatId, "Привет! Я бот для мониторинга цен." +
                        " Ты успешно зарегистрирован.");
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
