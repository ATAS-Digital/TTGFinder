package ru.atas.TRPfinder.Bot.Commands;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.atas.TRPfinder.Bot.Interfaces.CommandInterface;

import java.util.ArrayList;
import java.util.List;

@Component
public class EventCommand implements CommandInterface {

    @Override
    public SendMessage doAction(Update update) {
        var message = new SendMessage();
        long chatId = update.getMessage().getChatId();
        message.setChatId(chatId);

        message.setReplyMarkup(MakeKeyboard());
        message.setText("Что именно вы хотите сделать?");
        return message;
    }

    private InlineKeyboardMarkup MakeKeyboard(){
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
        List<InlineKeyboardButton> firstRowInLine = new ArrayList<>();
        List<InlineKeyboardButton> secondRowInLine = new ArrayList<>();

        var newGame = new InlineKeyboardButton();
        newGame.setText("Новая игра");
        newGame.setCallbackData("newGame");
        firstRowInLine.add(newGame);

        var seeGames = new InlineKeyboardButton();
        seeGames.setText("Список игр");
        seeGames.setCallbackData("allGames");
        firstRowInLine.add(seeGames);

        rowsInLine.add(firstRowInLine);

        var myGames = new InlineKeyboardButton();
        myGames.setText("Мои игры");
        myGames.setCallbackData("myGames");
        secondRowInLine.add(myGames);

        rowsInLine.add(secondRowInLine);

        keyboardMarkup.setKeyboard(rowsInLine);

        return keyboardMarkup;
    }
}
