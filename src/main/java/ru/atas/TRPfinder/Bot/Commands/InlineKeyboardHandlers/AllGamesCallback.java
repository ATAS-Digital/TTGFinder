package ru.atas.TRPfinder.Bot.Commands.InlineKeyboardHandlers;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.atas.TRPfinder.Bot.Interfaces.EventCallbackInterface;
import ru.atas.TRPfinder.Entities.GameEvent;
import ru.atas.TRPfinder.Services.GameEventService;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class AllGamesCallback implements EventCallbackInterface {

    private int size;
    private static int iterations;
    private int additionalIterations;
    List<GameEvent> gamesList;
    InlineKeyboardMarkup keyboard;
    List<List<InlineKeyboardButton>> rowsInLine;
    List<InlineKeyboardButton> bottomRowsInLine;
    StringBuilder builder;
    int lastIndexOfIterations;

    private final GameEventService gameEventService;

    public AllGamesCallback(GameEventService gameEventService){
        this.gameEventService = gameEventService;
        keyboard = new InlineKeyboardMarkup();
        lastIndexOfIterations = 0;
    }

    private EditMessageText editMessagePREV(Update update) {
        gamesList = gameEventService.getAllGames();
        size = gamesList.size();
        iterations = size / 5;
        additionalIterations = size % 5;

        EditMessageText editedMessage = new EditMessageText();
        editedMessage.setMessageId(update.getCallbackQuery().getMessage().getMessageId());
        editedMessage.setChatId(update.getCallbackQuery().getMessage().getChatId());

        var keyboard = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
        List<InlineKeyboardButton> bottomRowsInLine = new ArrayList<>();

        builder = new StringBuilder();

        if (size >= 5) {
            for(int i = 0; i < 5; i++){
                builder.append(String.format("""
                    %s
                    [ %s ]
                    "%s"\n
                    """, gamesList.get(i).getName(), gamesList.get(i).getDate()
                                .format(DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mm z")),
                        gamesList.get(i).getDescription()));

                var gameButton = new InlineKeyboardButton();
                gameButton.setText(gamesList.get(i).getName());
                gameButton.setCallbackData(String.format("game%s", gamesList.get(i).getId()));
                List<InlineKeyboardButton> rowInLine = new ArrayList<>();
                rowInLine.add(gameButton);
                rowsInLine.add(rowInLine);
            }
            editedMessage.setText(builder.toString());
            iterations -= 1;
            lastIndexOfIterations = 4;
        }
        else{
            for(int i = 0; i < size; i++){
                builder.append(String.format("""
                    %s
                    [ %s ]
                    "%s"\n
                    """, gamesList.get(i).getName(), gamesList.get(i).getDate()
                                .format(DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mm z")),
                        gamesList.get(i).getDescription()));

                var gameButton = new InlineKeyboardButton();
                gameButton.setText(gamesList.get(i).getName());
                gameButton.setCallbackData(String.format("game%s", gamesList.get(i).getId()));
                List<InlineKeyboardButton> rowInLine = new ArrayList<>();
                rowInLine.add(gameButton);
                rowsInLine.add(rowInLine);
            }
            editedMessage.setText(builder.toString());
        }

        if (lastIndexOfIterations / 5 > 1){
            var prevButton = new InlineKeyboardButton();
            prevButton.setText("[дальше]");
            prevButton.setCallbackData("next");
            bottomRowsInLine.add(prevButton);
            rowsInLine.add(bottomRowsInLine);
        }
        var nextButton = new InlineKeyboardButton();
        nextButton.setText("[дальше]");
        nextButton.setCallbackData("next");
        bottomRowsInLine.add(nextButton);
        rowsInLine.add(bottomRowsInLine);

        keyboard.setKeyboard(rowsInLine);
        editedMessage.setReplyMarkup(keyboard);

        return editedMessage;
    }

    private EditMessageText editMessageNEXT(Update update) {
        gamesList = gameEventService.getAllGames();
        int size = gamesList.size();
        additionalIterations = size % 5;
        rowsInLine = new ArrayList<>();
        bottomRowsInLine = new ArrayList<>();
        builder = new StringBuilder();

        EditMessageText editedMessage = new EditMessageText();
        editedMessage.setMessageId(update.getCallbackQuery().getMessage().getMessageId());
        editedMessage.setChatId(update.getCallbackQuery().getMessage().getChatId());

        int index;

        if(iterations > 0){
            for (int i = 0; i < 5; i++){
                index = i + lastIndexOfIterations;
                builder.append(String.format("""
                    %s
                    [ %s ]
                    "%s"\n
                    """, gamesList.get(index).getName(), gamesList.get(index).getDate()
                                .format(DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mm z")),
                        gamesList.get(i).getDescription()));

                var gameButton = new InlineKeyboardButton();
                gameButton.setText(gamesList.get(index).getName());
                gameButton.setCallbackData(String.format("game%s", gamesList.get(index).getId()));
                List<InlineKeyboardButton> rowInLine = new ArrayList<>();
                rowInLine.add(gameButton);
                rowsInLine.add(rowInLine);
            }
            editedMessage.setText(builder.toString());
            iterations -= 1;
            lastIndexOfIterations += 5;
        }
        else {
            for(int i = 0; i < additionalIterations; i++){
                builder.append(String.format("""
                    %s
                    [ %s ]
                    "%s"\n
                    """, gamesList.get(i).getName(), gamesList.get(i).getDate()
                                .format(DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mm z")),
                        gamesList.get(i).getDescription()));

                var gameButton = new InlineKeyboardButton();
                gameButton.setText(gamesList.get(i).getName());
                gameButton.setCallbackData(String.format("game%s", gamesList.get(i).getId()));
                List<InlineKeyboardButton> rowInLine = new ArrayList<>();
                rowInLine.add(gameButton);
                rowsInLine.add(rowInLine);

                additionalIterations -= 1;
            }
            editedMessage.setText(builder.toString());
        }

        var prevButton = new InlineKeyboardButton();
        prevButton.setText("[назад]");
        prevButton.setCallbackData("prev");
        bottomRowsInLine.add(prevButton);

        if (iterations - 1 > 0 || additionalIterations > 0) {
            var nextButton = new InlineKeyboardButton();
            nextButton.setText("[дальше]");
            nextButton.setCallbackData("next");
            bottomRowsInLine.add(nextButton);
        }
        rowsInLine.add(bottomRowsInLine);

        keyboard.setKeyboard(rowsInLine);
        editedMessage.setReplyMarkup(keyboard);

        return editedMessage;
    }

    @Override
    public EditMessageText editMessage(Update update) {
        EditMessageText editedMessage;
        if (update.getCallbackQuery().getData().equals("next")){
                return editMessageNEXT(update);
        }
        else
            editedMessage = editMessagePREV(update);

        return editedMessage;
    }

    // вывод по 5 и менее пунктов вместе с клавиатурой,
    // через которую можно провалится ниже,
    // чтобы посмотреть инфу об игре и записаться, если нужно (со возможностью вернутся к списку)
    @Override
    public SendMessage sendMessage(Update update) {
        gamesList = gameEventService.getAllGames();
        size = gamesList.size();
        iterations = size / 5;
        additionalIterations = size % 5;

        SendMessage message = new SendMessage();
        message.setChatId(update.getCallbackQuery().getMessage().getChatId());

        var keyboard = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
        List<InlineKeyboardButton> bottomRowsInLine = new ArrayList<>();

        builder = new StringBuilder();

        if (size >= 5) {
            for(int i = 0; i < 5; i++){
                builder.append(String.format("""
                    %s
                    [ %s ]
                    "%s"\n
                    """, gamesList.get(i).getName(), gamesList.get(i).getDate()
                                .format(DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mm z")),
                        gamesList.get(i).getDescription()));

                var gameButton = new InlineKeyboardButton();
                gameButton.setText(gamesList.get(i).getName());
                gameButton.setCallbackData(String.format("game%s", gamesList.get(i).getId()));
                List<InlineKeyboardButton> rowInLine = new ArrayList<>();
                rowInLine.add(gameButton);
                rowsInLine.add(rowInLine);
            }
            message.setText(builder.toString());
            iterations -= 1;
            lastIndexOfIterations = 5;
        }
        else{
            for(int i = 0; i < size; i++){
                builder.append(String.format("""
                    %s
                    [ %s ]
                    "%s"\n
                    """, gamesList.get(i).getName(), gamesList.get(i).getDate()
                                .format(DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mm z")),
                        gamesList.get(i).getDescription()));

                var gameButton = new InlineKeyboardButton();
                gameButton.setText(gamesList.get(i).getName());
                gameButton.setCallbackData(String.format("game%s", gamesList.get(i).getId()));
                List<InlineKeyboardButton> rowInLine = new ArrayList<>();
                rowInLine.add(gameButton);
                rowsInLine.add(rowInLine);
            }
            message.setText(builder.toString());
        }
        if (iterations - 1 > 0 || additionalIterations > 0) {
            var nextButton = new InlineKeyboardButton();
            nextButton.setText("[дальше]");
            nextButton.setCallbackData("next");
            bottomRowsInLine.add(nextButton);
            rowsInLine.add(bottomRowsInLine);
        }

        keyboard.setKeyboard(rowsInLine);
        message.setReplyMarkup(keyboard);

        return message;
    }
}
