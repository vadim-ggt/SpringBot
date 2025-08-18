package com.Bot5wProj.SpringBotTGmy.service;

import com.Bot5wProj.SpringBotTGmy.model.Product;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.springframework.stereotype.Service;

@Service
public class BotCommandHandler {

    private final MessageSender messageSender;
    private final UserService userService;
    private final ProductService productService;

    public BotCommandHandler(MessageSender messageSender, UserService userService, ProductService productService) {
         this.messageSender = messageSender;
         this.userService = userService;
         this.productService = productService;
    }

    public void handleCommand(Message message) {

        Long chatId = message.getChatId();
        String command = message.getText();
        String username = message.getFrom().getUserName();
        System.out.println("Received command: '" + command + "'");

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
            case "/track": {
                messageSender.sendMessage(chatId, "Отправьте артикул товара с Wildberries");
                break;
            }
            default: {
                if (command.matches("\\d+")) { // если только цифры → артикул
                    try {
                        Product product = productService.fetchAndSaveProduct(chatId, command);
                        messageSender.sendMessage(chatId,
                                "Товар добавлен: " + product.getTitle() + " | " + product.getPrice() + "₽");
                    } catch (Exception e) {
                        messageSender.sendMessage(chatId, "Ошибка: не удалось получить товар по артикулу");
                    }
                } else {
                    messageSender.sendMessage(chatId, "Неизвестная команда. Используйте /help");
                }
            }        }

    }
}
