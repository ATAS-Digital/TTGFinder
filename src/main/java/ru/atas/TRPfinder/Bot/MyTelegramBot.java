package ru.atas.TRPfinder.Bot;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ru.atas.TRPfinder.Bot.Commands.*;
import ru.atas.TRPfinder.Bot.Commands.InlineKeyboardHandlers.NewGameCommand;
import ru.atas.TRPfinder.Bot.Interfaces.CommandInterface;
import ru.atas.TRPfinder.Bot.Interfaces.EventCallbackInterface;
import ru.atas.TRPfinder.Services.GameEventService;
import ru.atas.TRPfinder.Services.PlayerService;

import java.util.HashMap;

@Component
public class MyTelegramBot extends TelegramLongPollingBot {

    @Autowired
    private PlayerService playerService;

    @Autowired
    private GameEventService gameEventService;

    private HashMap<String, CommandInterface> commandInterfaceMap;

    private HashMap<String, EventCallbackInterface> callbackButtonsMap;

    @PostConstruct
    public void registerBot() {
        TelegramBotsApi botApi;
        try {
            botApi = new TelegramBotsApi(DefaultBotSession.class);
            botApi.registerBot(this);
        } catch (TelegramApiException e) {
            System.out.println(e.getMessage());
        }

        commandInterfaceMap = new HashMap<>();
        commandInterfaceMap.put("other", new DefaultAnswer());
        commandInterfaceMap.put("/start", new StartCommand());
        commandInterfaceMap.put("/login", new LoginCommand(playerService));
        commandInterfaceMap.put("/profile", new ProfileCommand(playerService));
        commandInterfaceMap.put("/events", new EventCommand());
        commandInterfaceMap.put("getTimeData", new TimeData(gameEventService));

        callbackButtonsMap = new HashMap<>();
        callbackButtonsMap.put("newGame", new NewGameCommand());

    }



    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {

            String messageText = update.getMessage().getText();

            System.out.println(update.getMessage().getChat().getUserName() + ": " + messageText);

            if (commandInterfaceMap.containsKey(messageText)) {
                executeMessage(commandInterfaceMap.get(messageText).doAction(update));
            }
            else if (update.getMessage().getText().contains("UTC")){
                executeMessage(commandInterfaceMap.get("getTimeData").doAction(update));
            }
            else {
                executeMessage(commandInterfaceMap.get("other").doAction(update));
            }

        }
        else if (update.hasCallbackQuery()) {
            String callData = update.getCallbackQuery().getData();
            executeEditMessage(callbackButtonsMap.get(callData).getMessage(update));
        }
    }

    public void executeEditMessage(EditMessageText message) {
        try {
            execute(message);
        } catch (TelegramApiException e) {
            System.out.println("ERROR! " + "Ошибка при отправке сообщения: " + e.getMessage());
        }
    }

    public void executeMessage(SendMessage message) {
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
