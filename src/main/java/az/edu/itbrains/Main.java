package az.edu.itbrains;

import az.edu.itbrains.botGui.TelegramBotGUI;
import az.edu.itbrains.bots.GameBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Main {
    public static void main(String[] args) {
        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);

            telegramBotsApi.registerBot(new GameBot());
        }catch (Exception e){
            System.out.println(e.getMessage());


        }


        try {

            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            GameBot bot = new GameBot();
            telegramBotsApi.registerBot(bot);


            javax.swing.SwingUtilities.invokeLater(() -> new TelegramBotGUI(bot));

        } catch (Exception e) {
            System.out.println(" Xəta baş verdi: " + e.getMessage());
        }
    }
    }
