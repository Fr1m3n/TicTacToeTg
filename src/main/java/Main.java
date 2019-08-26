import Utils.DatabaseManager;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.generics.BotSession;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Main {

    public static void main(String[] args) {
        DatabaseManager databaseManager = new DatabaseManager();
        databaseManager.init();
        ApiContextInitializer.init();
        TelegramBotsApi tgBotsApi = new TelegramBotsApi();
        try {
            BotSession session = tgBotsApi.registerBot(new Bot());
        } catch (TelegramApiRequestException e) {
            // TODO: обработать исключение
            e.printStackTrace();
        }
    }
}
