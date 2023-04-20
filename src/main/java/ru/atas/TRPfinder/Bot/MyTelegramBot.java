package ru.atas.TRPfinder.Bot;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ru.atas.TRPfinder.Entities.Player;
import ru.atas.TRPfinder.Records.PlayerRecord;
import ru.atas.TRPfinder.Services.PlayerService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Component
public class MyTelegramBot extends TelegramLongPollingBot {

    @Autowired
    private PlayerService playerService;

    @PostConstruct
    public void registerBot() {
        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(this);
        } catch (TelegramApiException e) {
            System.out.println(e);
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {

            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();
            String firstName = update.getMessage().getChat().getFirstName();

            StringBuilder sb;
            sb = new StringBuilder(update.getMessage().getChat().getUserName() + ": " + messageText);

            System.out.println(sb.toString());

            switch (messageText) {
                case "/start":
                    startCommand(chatId, firstName);
                    break;
                case "/players":
                    getPlayersCommand(chatId);
                    break;
                case "/login":
                    getLogin(update, chatId);
                    break;
                case "/profile":
                    getUserData(chatId);
                    break;
                default:
                    sendDefaultMessage(chatId);
            }
        }
    }

    private void getUserData(long chatId) {
        String text;
        try{
            var player = playerService.getPlayerById(chatId);
            text = "Ваши данные:" + "\n\n" +
                    "name: " + player.getName() + "\n" +
                    "id: " + player.getId();
        }
        catch (NoSuchElementException e){
            System.out.println("ERROR!! Запись не найдена в БД: " + e.getMessage());
            text = "Вы ещё не зарегистрировались :)";
        }

        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(text);
        executeMessage(message);
    }

    private void getLogin(Update update, long chatId) {
        String name =update.getMessage().getChat().getFirstName();
        SendMessage message = new SendMessage();
        message.setChatId(chatId);

        var playerRecord = new PlayerRecord(
                chatId,
                name);

        if (playerService.addPlayer(playerRecord)) {
            message.setText("Вы успешно зарегистрированы. Ваш никнейм: " + name);
        }
        else {
            message.setText("Вы уже зарегистрированы под никнеймом: " + name);
        }
        executeMessage(message);
    }

    private void getPlayersCommand(long chatId) {
        SendMessage message = new SendMessage();
        List<Player> players = playerService.getPlayers().stream().toList();
        StringBuilder text = new StringBuilder();

        text.append("Список игроков:" + "\n\n");
        for (int i = 0; i < players.size(); i++) {
            text.append("\t* Игрок" + " №" + (i + 1) + ": " + players.get(i).toString());
            text.append("\n");
        }
        message.setChatId(chatId);
        message.setText(text.toString());

        executeMessage(message);
    }

    private void sendDefaultMessage(long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("Извините, я не знаю такой команды. :(\n\nПопробуйте написать команду из доступных.");
        executeMessage(message);
    }

    private void startCommand(long chatId, String name) {
        String answer = "Привет, " + name + "! Рады с тобой познакомится!!";
        sendMessage(answer, chatId);
    }

    private void sendMessage(String answer, long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(answer);
        executeMessage(message);
    }

    private void executeMessage(SendMessage message) {
        try {
            execute(message);
        } catch (TelegramApiException e) {
            System.out.println("ERROR! " + "Ошибка при отправке сообщения: " + e.getMessage());
        }
    }

    @Override
    public String getBotToken() {
        // Вынесено в системные переменные
        // BOT_TOKEN, TEST_BOT_TOKEN
        return System.getenv("BOT_TOKEN");
    }

    @Override
    public String getBotUsername() {
        // Вынесено в системные переменные
        //BOT_NAME, TEST_BOT_NAME
        return System.getenv("BOT_NAME");
    }
}
