package ru.atas.TRPfinder.Bot.Interfaces;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface EventCallbackInterface {
    EditMessageText getMessage(Update update);
    SendMessage sendMessage(Update update);
}
