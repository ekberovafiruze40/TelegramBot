package az.edu.itbrains.bots;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.sql.Time;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TestBot extends TelegramLongPollingBot {
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    private boolean isMesssagingRunning = false;

    @Override
    public String getBotUsername() {
        return "MeleykeCute_Bot";
    }

    @Override
    public String getBotToken() {
        return "7294082354:AAGf1EuwRhAGEgfBHrAUPhyAcjsi5W0q6d4";
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()){
            String messageText = update.getMessage().getText();
            Long chatId = update.getMessage().getChatId();

            switch (messageText){
                case "/start":
                    if (!isMesssagingRunning){
                        isMesssagingRunning = true;
                        sendMessage(chatId, "Automatic messaging started!");
                        startMessageScheduler(chatId);
                    }else {
                    sendMessage(chatId, "Messaging is already running!");
                    }
                    break;
                case "/stop":
                    if (isMesssagingRunning){
                        isMesssagingRunning = false;
                        stopMessageScheduler();
                        sendMessage(chatId, "Automatic messaging stopped!");
                    }else {
                        sendMessage(chatId, "Messaging is not running!");
                    }
                    break;
                default:
                    sendMessage(chatId, "Unknown command!");
            }
        }

    }
    private void startMessageScheduler(long chatId){
        scheduler.scheduleAtFixedRate(()->{
            if (isMesssagingRunning){
                sendMessage(chatId, "This is an automated message sent every 10seconds.");
            }
        }, 0,10, TimeUnit.SECONDS);
    }


    private void stopMessageScheduler(){
        scheduler.shutdownNow();
    }


    private void sendMessage(Long chatId, String text){
        SendMessage message = new SendMessage();
        message.setChatId(chatId.toString());
        message.setText(text);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
