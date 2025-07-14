package az.edu.itbrains.bots;

import az.edu.itbrains.models.Player;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.polls.SendPoll;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.polls.PollAnswer;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class GameBot extends TelegramLongPollingBot {
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    private boolean isMesssagingRunning = false;

    private List<Player> players = new ArrayList<>();


    private int count = 0;

    private int gamePeriod = 0;


    private boolean start = false;



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
          Long chatId = update.getMessage().getChatId();
          if (count != 3 && !start) {
              menu(update);
          }


          if (count==3){
              start=true;
          }
          if (start){
              boolean result = sendRoles(getPlayers());
              if (result){
                  try {
                      SendMessage message = new SendMessage();
                      message.setChatId(String.valueOf(chatId));
                      if (!isMesssagingRunning && gamePeriod<=2){
                          message.setText("Oyun basladi. 5 deqiqe vaxtiniz var muzakire edin.");
                          System.out.println(gamePeriod);
                          startMessageScheduler(chatId);
                          gamePeriod++;
                          isMesssagingRunning = true;
                      }else {
                          sendMessage(chatId, "Messaging is already running!");
                      }
                      execute(message);
                  }catch (Exception e){

                  }
              }
              start=false;
              count++;

          }



      }

    }

    private void menu(Update update) {
        switch (update.getMessage().getText()) {
            case "/start":
                Long id = update.getMessage().getFrom().getId();
                String name = update.getMessage().getFrom().getFirstName();
                String surname = update.getMessage().getFrom().getUserName();
                Player findPlayer = getById(id);

                if (findPlayer == null) {
                    Player player = new Player();
                    player.setId(id);
                    player.setName(name);
                    addPlayer(player);
                    count++;

                } else {
                    System.out.println("Nagarsan?");
                }
                break;
            case " /info":


                break;
            case " /ads":
                break;
        }
    }


    private boolean sendRoles(List<Player> players){

        try {
            Random random = new Random();
            List<String> roles = new ArrayList<>();
            roles.add("Limon");
            roles.add("Limon");
            roles.add("agent");

            int rnd = random.nextInt(0,roles.size());
            SendMessage message = new SendMessage();



            for (int i = 0; i < players.size(); i++) {
//                System.out.println("Chat id: " + players.get(i).getId() + "\nUsername: " + players.get(i).getName() + "\nRole: " + roles.get(rnd));
               message.setText("This for lesson and message send testing for student sorry for if message send you accidentally.\nYour game role is: " + roles.get(rnd));
               message.setChatId(String.valueOf(players.get(i).getId()));

               execute(message);

                if (i < 3){
                    roles.remove(rnd);
                    rnd = random.nextInt(0,roles.size());
                }
            }

            return true;


        }catch (Exception e){
            return false;
        }

    }

    private void addPlayer(Player player){
        List<Player> playerList = getPlayers();
        playerList.add(player);
    }


    private List<Player> getPlayers(){
        return players;
    }



    private Player getById(Long id){
      try {
         Player player = players.stream().filter(x->x.getId().equals(id)).findFirst().orElseThrow();

          return player;

      }catch (Exception e){
          return null;
      }
    }

    private void startMessageScheduler(long chatId){
        scheduler.scheduleAtFixedRate(()->{
            if (isMesssagingRunning){
                if (gamePeriod == 1){
                    sendMessage(chatId, "Muzakire basladi");

                } else if (gamePeriod == 2) {
                    sendMessage(chatId, "Sesverme basladi");
                    sendVoid(chatId);

                }else {
                    sendMessage(chatId, "Oyun bitdi");
                    isMesssagingRunning = false;
                }
                gamePeriod++;
            }
        }, 0,120, TimeUnit.SECONDS);
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


    private void sendVoid(Long chatId){
        SendPoll sendPoll = new SendPoll();
        sendPoll.setChatId(String.valueOf(chatId));
        sendPoll.setQuestion("Agent kimdir?");
        sendPoll.setCorrectOptionId(1);
        List<String> users = players.stream().map(x->x.getName()).collect(Collectors.toList());
        sendPoll.setOptions(users);
        sendPoll.setCloseDate(1);

        try {
            execute(sendPoll);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

}
