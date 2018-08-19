package sincity.bot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.starter.EnableTelegramBots;
import sincity.bot.handler.Bot;

@SpringBootApplication
@EnableTelegramBots
public class BotApplication {

    public static void main(String[] args) {
        ApiContextInitializer.init();
        SpringApplication.run(BotApplication.class, args);
    }
}
