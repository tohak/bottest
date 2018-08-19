package sincity.bot.config;

        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.boot.ApplicationArguments;
        import org.springframework.boot.ApplicationRunner;
        import org.springframework.stereotype.Component;
        import org.telegram.telegrambots.TelegramBotsApi;
        import sincity.bot.handler.Bot;
@Component
public class AppStartupRunner implements ApplicationRunner {
    private final TelegramBotsApi telegramBotsApi;
    private final Bot bot;

    @Autowired
    public AppStartupRunner(TelegramBotsApi telegramBotsApi, Bot bot) {
        this.telegramBotsApi = telegramBotsApi;
        this.bot = bot;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        telegramBotsApi.registerBot(bot);
    }
}
