package ru.atas.TRPfinder.Bot.Commands.InlineKeyboardHandlers;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.atas.TRPfinder.Bot.Interfaces.EventCallbackInterface;
import ru.atas.TRPfinder.Services.GameEventService;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class GameInfoCallback implements EventCallbackInterface {

    private final GameEventService gameEventService;

    public GameInfoCallback(GameEventService gameEventService){
        this.gameEventService = gameEventService;
    }

    // здесь пока не используется
    @Override
    public EditMessageText editMessage(Update update) {
        EditMessageText editedMessage = new EditMessageText();
        editedMessage.setChatId(update.getCallbackQuery().getMessage().getChatId());
        editedMessage.setMessageId(update.getCallbackQuery().getMessage().getMessageId());

        String callData = update.getCallbackQuery().getData();
        long gameID = Long.parseLong(callData.substring(4));
        var game = gameEventService.getGameById(gameID);

        var text = String.format("""
                Название: %s
                
                Дата и время: %s
                
                Описание: %s
                
                Контакт мастера: @%s
                """,
                game.getName(),
                game.getDate().format(DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mm z")),
                game.getDescription(),
                gameEventService.getMasterName(gameID));

        editedMessage.setText(text);

        var keyboard = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();

        var registerButton = new InlineKeyboardButton();
        registerButton.setText("Записаться");
        registerButton.setCallbackData(String.format("signUp %s", gameID));
        List<InlineKeyboardButton> firstLine = new ArrayList<>();
        firstLine.add(registerButton);
        rowsInLine.add(firstLine);

        var closeButton = new InlineKeyboardButton();
        closeButton.setText("[X] закрыть");
        closeButton.setCallbackData("close");
        List<InlineKeyboardButton> secondLine = new ArrayList<>();
        secondLine.add(closeButton);
        rowsInLine.add(secondLine);

        keyboard.setKeyboard(rowsInLine);
        editedMessage.setReplyMarkup(keyboard);

        return editedMessage;
    }

    public DeleteMessage deleteMessage(Update update){
        DeleteMessage messageToDelete = new DeleteMessage();
        messageToDelete.setMessageId(update.getCallbackQuery().getMessage().getMessageId());
        messageToDelete.setChatId(update.getCallbackQuery().getMessage().getChatId());
        return messageToDelete;
    }

    @Override
    public SendMessage sendMessage(Update update) {
        SendMessage message = new SendMessage();
        message.setChatId(update.getCallbackQuery().getMessage().getChatId());
        String callData = update.getCallbackQuery().getData();
        long gameID = Long.parseLong(callData.substring(4));
        var game = gameEventService.getGameById(gameID);

        var text = String.format("""
                Название: %s
                
                Дата и время: %s
                
                Описание: %s
                
                Контакт мастера: @%s
                """,
                game.getName(),
                game.getDate().format(DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mm z")),
                game.getDescription(),
                gameEventService.getMasterName(gameID));

        message.setText(text);

        var keyboard = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();

        var registerButton = new InlineKeyboardButton();
        registerButton.setText("Записаться");
        registerButton.setCallbackData(String.format("signUp %s", gameID));
        List<InlineKeyboardButton> firstLine = new ArrayList<>();
        firstLine.add(registerButton);
        rowsInLine.add(firstLine);

        var closeButton = new InlineKeyboardButton();
        closeButton.setText("[X] закрыть");
        closeButton.setCallbackData("close");
        List<InlineKeyboardButton> secondLine = new ArrayList<>();
        secondLine.add(closeButton);
        rowsInLine.add(secondLine);

        keyboard.setKeyboard(rowsInLine);
        message.setReplyMarkup(keyboard);

        return message;
    }

    @Override
    public AnswerCallbackQuery answerCallback(Update update) {
        AnswerCallbackQuery answer = new AnswerCallbackQuery();
        answer.setCallbackQueryId(update.getCallbackQuery().getId());
        return answer;
    }
}
