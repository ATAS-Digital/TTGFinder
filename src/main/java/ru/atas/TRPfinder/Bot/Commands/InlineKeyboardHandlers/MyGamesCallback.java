package ru.atas.TRPfinder.Bot.Commands.InlineKeyboardHandlers;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.atas.TRPfinder.Bot.Interfaces.EventCallbackInterface;

@Component
public class MyGamesCallback implements EventCallbackInterface {

    @Override
    public EditMessageText editMessage(Update update) {
        return null;
    }

    @Override
    public SendMessage sendMessage(Update update) {
        return null;
    }

    @Override
    public DeleteMessage deleteMessage(Update update) {
        return null;
    }
}
