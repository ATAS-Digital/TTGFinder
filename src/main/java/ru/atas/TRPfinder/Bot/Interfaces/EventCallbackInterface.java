package ru.atas.TRPfinder.Bot.Interfaces;

import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface EventCallbackInterface {
    EditMessageText editMessage(Update update);
    SendMessage sendMessage(Update update);

    AnswerCallbackQuery answerCallback(Update update);

    DeleteMessage deleteMessage(Update update);
}
