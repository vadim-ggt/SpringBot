package com.Bot5wProj.SpringBotTGmy.config;



import lombok.Data;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.beans.factory.annotation.Value;

@Configuration
@Data
public class BotConfig {

    @Value("${bot.token}")
    String token;

    @Value("${bot.name}")
    String botName;

}
